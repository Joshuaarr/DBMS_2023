<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
    <style>
        /* Basic CSS for the vertical navigation bar */
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .navbar {
            width: 300px; /* Set the width of the vertical navbar */
            background-color: #333;
            height: 100%; /* Full height */
            position: fixed; /* Fixed sidebar (stay in place) */
            overflow: auto; /* Scrollable area */
        }
        .navbar a {
            padding: 16px 16px;
            text-decoration: none;
            font-size: 18px;
            color: #f2f2f2;
            display: block;
        }
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }
        .content {
        	margin-left: 350px;
        }
        .para {
        	color: white;
        	background-color: black;
        }
    </style>
</head>
<body>
	<div class="navbar">
	    <a href="manageAccount.jsp">Account</a>
	    <a href="manageCharacter.jsp">Character</a>
	    <a href="manageEquippedItem.jsp">Equipped Item</a>
	    <a href="manageInventory.jsp">Inventory</a>
	    <a href="manageJobItems.jsp">Job Items</a>
	    <a href="manageCurrencyItem.jsp">Currency Item</a>
	    <a href="manageCustomizedEquipment.jsp">Customized Equipment</a>
	    <br/>
<!-- 	    <a href="manageEquipment.jsp">Equipment</a> ****Need to Add Weapon Here****--> 
	    <a href="manageGear.jsp">Gear</a>
	    <a href="manageConsumable.jsp">Consumable</a>
	    <a href="manageMiscellaneous.jsp">Miscellaneous</a>
	    <a href="manageEquipmentBonus.jsp">Equipment Bonus</a>
	    <a href="manageConsumableBonus.jsp">Consumable Bonus</a>
	    <br/>
	    <a href="manageCurrency.jsp">Currency</a>
	    <a href="manageJobLevel.jsp">Job Level</a>
	    <a href="manageJob.jsp">Job</a>
	    <a href="manageGearsForJobs.jsp">Gears For Jobs</a>
	</div>
	<div class="content">
	    <h2>CS5200 Project Demo</h2>
	</div>

</body>
</html>