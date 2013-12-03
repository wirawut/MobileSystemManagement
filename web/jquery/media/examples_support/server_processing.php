<?php
  /* MySQL connection */
	include( $_SERVER['DOCUMENT_ROOT']."/datatables/mysql.php" ); /* ;-) */
	
	$gaSql['link'] =  mysql_pconnect( $gaSql['server'], $gaSql['user'], $gaSql['password']  ) or
		die( 'Could not open connection to server' );
	
	mysql_select_db( $gaSql['db'], $gaSql['link'] ) or 
		die( 'Could not select database '. $gaSql['db'] );
	
	/* Paging */
	$sLimit = "";
	if ( isset( $_GET['iDisplayStart'] ) )
	{
		$sLimit = "LIMIT ".mysql_real_escape_string( $_GET['iDisplayStart'] ).", ".
			mysql_real_escape_string( $_GET['iDisplayLength'] );
	}
	
	/* Ordering */
	if ( isset( $_GET['iSortCol_0'] ) )
	{
		$sOrder = "ORDER BY  ";
		for ( $i=0 ; $i<mysql_real_escape_string( $_GET['iSortingCols'] ) ; $i++ )
		{
			$sOrder .= fnColumnToField(mysql_real_escape_string( $_GET['iSortCol_'.$i] ))."
			 	".mysql_real_escape_string( $_GET['iSortDir_'.$i] ) .", ";
		}
		$sOrder = substr_replace( $sOrder, "", -2 );
	}
	
	/* Filtering */
	$sWhere = "";
	if ( mysql_real_escape_string( $_GET['sSearch'] ) != "" )
	{
		$sWhere = "WHERE engine LIKE '%".mysql_real_escape_string( $_GET['sSearch'] )."%' OR ".
		                "browser LIKE '%".mysql_real_escape_string( $_GET['sSearch'] )."%' OR ".
		                "platform LIKE '%".mysql_real_escape_string( $_GET['sSearch'] )."%' OR ".
		                "version LIKE '%".mysql_real_escape_string( $_GET['sSearch'] )."%' OR ".
		                "grade LIKE '%".mysql_real_escape_string( $_GET['sSearch'] )."%'";
	}
	
	$sQuery = "
		SELECT id, engine, browser, platform, version, grade
		FROM   ajax
		$sWhere
		$sOrder
		$sLimit
	";
	$rResult = mysql_query( $sQuery, $gaSql['link'] ) or die(mysql_error());
	
	$sQuery = "
		SELECT id
		FROM   ajax
	";
	$rResultTotal = mysql_query( $sQuery, $gaSql['link'] ) or die(mysql_error());
	$iTotal = mysql_num_rows($rResultTotal);
	
	if ( $sWhere != "" )
	{
		$sQuery = "
			SELECT id
			FROM   ajax
			$sWhere
		";
		$rResultFilterTotal = mysql_query( $sQuery, $gaSql['link'] ) or die(mysql_error());
		$iFilteredTotal = mysql_num_rows($rResultFilterTotal);
	}
	else
	{
		$iFilteredTotal = $iTotal;
	}
	
	$sOutput = '{';
	$sOutput .= '"iTotalRecords": '.$iTotal.', ';
	$sOutput .= '"iTotalDisplayRecords": '.$iFilteredTotal.', ';
	$sOutput .= '"aaData": [ ';
	while ( $aRow = mysql_fetch_array( $rResult ) )
	{
		$sOutput .= "[";
		$sOutput .= "'".$aRow['engine']."',";
		$sOutput .= "'".$aRow['browser']."',";
		$sOutput .= "'".$aRow['platform']."',";
		$sOutput .= "'".$aRow['version']."',";
		$sOutput .= "'".$aRow['grade']."'";
		$sOutput .= "],";
	}
	$sOutput = substr_replace( $sOutput, "", -1 );
	$sOutput .= '] }';
	
	echo $sOutput;
	
	
	function fnColumnToField( $i )
	{
		if ( $i == 0 )
			return "engine";
		else if ( $i == 1 )
			return "browser";
		else if ( $i == 2 )
			return "platform";
		else if ( $i == 3 )
			return "version";
		else if ( $i == 4 )
			return "grade";
	}
?>