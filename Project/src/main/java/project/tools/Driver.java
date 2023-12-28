package project.tools;

import project.dao.*;
import project.model.*;
import project.model.Character;
import project.model.EquippedItem.SlotName;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;



public class Driver {

	public static void main(String[] args) throws SQLException {
		// ---------- DAO instances. ----------
		AccountDao accountDao = AccountDao.getInstance();
		AttributePowerDao attributePowerDao = AttributePowerDao.getInstance();
        CharacterDao characterDao = CharacterDao.getInstance();
        ConsumableBonusDao consumableBonusDao = ConsumableBonusDao.getInstance();
        ConsumableDao consumableDao = ConsumableDao.getInstance();
        CurrencyDao currencyDao = CurrencyDao.getInstance();
        CurrencyItemDao currencyItemDao = CurrencyItemDao.getInstance();
        CustomizedEquipmentDao customizedEquipmentDao = CustomizedEquipmentDao.getInstance();        
        EquippedItemDao equippedItemDao = EquippedItemDao.getInstance();
        EquipmentBonusDao equipmentBonusDao = EquipmentBonusDao.getInstance(); 
        MiscellaneousDao miscellaneousDao = MiscellaneousDao.getInstance();
        JobDao jobDao = JobDao.getInstance();
        JobLevelDao jobLevelDao = JobLevelDao.getInstance();
        JobItemsDao jobItemsDao = JobItemsDao.getInstance();
        GearDao gearDao = GearDao.getInstance();
        GearsForJobsDao gearsForJobsDao = GearsForJobsDao.getInstance();
        WeaponDao weaponDao = WeaponDao.getInstance();
        InventoryDao inventoryDao = InventoryDao.getInstance();
       
		
		
		// ---------- CREATE. ----------
        // Job
        Job job1 = new Job("Warrior");
        job1 = jobDao.create(job1);

        Job job2 = new Job("Mage");
        job2 = jobDao.create(job2);

        Job job3 = new Job("Archer");
        job3 = jobDao.create(job3);

        Job job4 = new Job("Priest");
        job4 = jobDao.create(job4);

        Job job5 = new Job("Thief");
        job5 = jobDao.create(job5);
        
        
        // Job Level
        JobLevel jobLevel1 = new JobLevel(0, 100, false);
        jobLevel1 = jobLevelDao.create(jobLevel1);

        JobLevel jobLevel2 = new JobLevel(0, 120, false);
        jobLevel2 = jobLevelDao.create(jobLevel2);

        JobLevel jobLevel3 = new JobLevel(0, 90, false);
        jobLevel3 = jobLevelDao.create(jobLevel3);

        JobLevel jobLevel4 = new JobLevel(0, 110, false);
        jobLevel4 = jobLevelDao.create(jobLevel4);

        JobLevel jobLevel5 = new JobLevel(0, 80, false);
        jobLevel5 = jobLevelDao.create(jobLevel5);
        
        
		// Account
        Account account1 = new Account("JohnDoes", "john.doe@example.com", true);
        account1 = accountDao.create(account1);
      
        Account account2 = new Account("JaneSmith", "jane.smith@example.com", true);
        account2 = accountDao.create(account2);

        Account account3 = new Account("AliceBrown", "alice.brown@example.com", false);
        account3 = accountDao.create(account3);

        Account account4 = new Account("BobWhite", "bob.white@example.com", true);
        account4 = accountDao.create(account4);

        Account account5 = new Account("CharlieGreen", "charlie.green@example.com", true);
        account5 = accountDao.create(account5);

		
		// Character
        Character character1 = new Character(account1, "John", "Doe");
        character1 = characterDao.create(character1);

        Character character2 = new Character(account2, "Jane", "Smith");
        character2 = characterDao.create(character2);

        Character character3 = new Character(account3, "Alice", "Brown");
        character3 = characterDao.create(character3);

        Character character4 = new Character(account4, "Bob", "White");
        character4 = characterDao.create(character4);

        Character character5 = new Character(account5, "Charlie", "Green");
        character5 = characterDao.create(character5);
		
		// Job Items
        JobItems jobItems1 = new JobItems(character1, job1, 50, jobLevel1, true);
        jobItems1 = jobItemsDao.create(jobItems1);

        JobItems jobItems2 = new JobItems(character2, job2, 30, jobLevel2, true);
        jobItems2 = jobItemsDao.create(jobItems2);

        JobItems jobItems3 = new JobItems(character3, job3, 20, jobLevel3, true);
        jobItems3 = jobItemsDao.create(jobItems3);

        JobItems jobItems4 = new JobItems(character4, job4, 70, jobLevel4, true);
        jobItems4 = jobItemsDao.create(jobItems4);

        JobItems jobItems5 = new JobItems(character5, job5, 40, jobLevel5, true);
        jobItems5 = jobItemsDao.create(jobItems5);
		
		// Gear
        Gear gear1 = new Gear("GearName1", 1, 300.0, 10, 8, 20, 10);
        gear1 = gearDao.create(gear1);

        Gear gear2 = new Gear("GearName2", 1, 300.0, 10, 2, 60, 50);
        gear2 = gearDao.create(gear2);

        Gear gear3 = new Gear("GearName3", 1, 300.0, 10, 3, 70, 30);
        gear3 = gearDao.create(gear3);

        Gear gear4 = new Gear("GearName4", 1, 300.0, 10, 4, 80, 20);
        gear4 = gearDao.create(gear4);

        Gear gear5 = new Gear("GearName5", 1, 300.0, 10, 5, 90, 60);
        gear5 = gearDao.create(gear5);
        
        // GearsForJobs
        GearsForJobs gearsForJobs1 = new GearsForJobs(gear1, job1);
        gearsForJobs1 = gearsForJobsDao.create(gearsForJobs1);

        GearsForJobs gearsForJobs2 = new GearsForJobs(gear2, job2);
        gearsForJobs2 = gearsForJobsDao.create(gearsForJobs2);

        GearsForJobs gearsForJobs3 = new GearsForJobs(gear3, job3);
        gearsForJobs3 = gearsForJobsDao.create(gearsForJobs3);

        GearsForJobs gearsForJobs4 = new GearsForJobs(gear4, job4);
        gearsForJobs4 = gearsForJobsDao.create(gearsForJobs4);

        GearsForJobs gearsForJobs5 = new GearsForJobs(gear5, job5);
        gearsForJobs5 = gearsForJobsDao.create(gearsForJobs5);
        
        
        // Weapon
        Weapon weapon1 = new Weapon("Weaopon1", 1, 60.0,5, 5, 150, 2.0, 2.0, job1);
        weapon1 = weaponDao.create(weapon1);

        Weapon weapon2 = new Weapon("Weaopon2", 1, 60.0,5, 5, 150, 2.0, 2.0, job2);
        weapon2 = weaponDao.create(weapon2);

        Weapon weapon3 = new Weapon("Weaopon3", 1, 60.0,5, 5, 90, 1.2, 2.8, job3);
        weapon3 = weaponDao.create(weapon3);

        Weapon weapon4 = new Weapon("Weaopon4", 1, 60.0,5, 5, 110, 1.7, 2.3, job4);
        weapon4 = weaponDao.create(weapon4);

        Weapon weapon5 = new Weapon("Weaopon5", 1, 60.0,5, 5, 130, 1.8, 2.2, job5);
        weapon5 = weaponDao.create(weapon5);
        
        
        // AttributePower
        AttributePower attributePower1 = new AttributePower(AttributePower.AttributeName.valueOf("Strength"), character1, 50);
        attributePower1 = attributePowerDao.create(attributePower1);

        AttributePower attributePower2 = new AttributePower(AttributePower.AttributeName.valueOf("Dexterity"), character1, 60);
        attributePower2 = attributePowerDao.create(attributePower2);

        AttributePower attributePower3 = new AttributePower(AttributePower.AttributeName.valueOf("Intelligence"), character1, 70);
        attributePower3 = attributePowerDao.create(attributePower3);

        AttributePower attributePower4 = new AttributePower(AttributePower.AttributeName.valueOf("Vitality"), character2, 80);
        attributePower4 = attributePowerDao.create(attributePower4);

        AttributePower attributePower5 = new AttributePower(AttributePower.AttributeName.valueOf("Dexterity"), character2, 90);
        attributePower5 = attributePowerDao.create(attributePower5);

        AttributePower attributePower6 = new AttributePower(AttributePower.AttributeName.valueOf("Strength"), character3, 65);
        attributePower6 = attributePowerDao.create(attributePower6);

        AttributePower attributePower7 = new AttributePower(AttributePower.AttributeName.valueOf("Vitality"), character4, 75);
        attributePower7 = attributePowerDao.create(attributePower7);

        AttributePower attributePower8 = new AttributePower(AttributePower.AttributeName.valueOf("Intelligence"), character5, 85);
        attributePower8 = attributePowerDao.create(attributePower8);

        AttributePower attributePower9 = new AttributePower(AttributePower.AttributeName.valueOf("Mind"), character5, 55);
        attributePower9 = attributePowerDao.create(attributePower9);

        AttributePower attributePower10 = new AttributePower(AttributePower.AttributeName.valueOf("Mind"), character4, 95);
        attributePower10 = attributePowerDao.create(attributePower10);
        
        
        // EquippedItem
        EquippedItem equippedItem11 = new EquippedItem(character1, EquippedItem.SlotName.valueOf("Head"), 1) ;
        equippedItem11 = equippedItemDao.create(equippedItem11);
        EquippedItem equippedItem12 = new EquippedItem(character1, EquippedItem.SlotName.valueOf("Body"), 2) ;
        equippedItem12 = equippedItemDao.create(equippedItem12);
        EquippedItem equippedItem13 = new EquippedItem(character1, EquippedItem.SlotName.valueOf("Feet"), 3) ;
        equippedItem13 = equippedItemDao.create(equippedItem13);
        EquippedItem equippedItem14 = new EquippedItem(character1, EquippedItem.SlotName.valueOf("Hands"), 4) ;
        equippedItem14 = equippedItemDao.create(equippedItem14);
        EquippedItem equippedItem15 = new EquippedItem(character1, EquippedItem.SlotName.valueOf("MainHand"), 5) ;
        equippedItem15 = equippedItemDao.create(equippedItem15);
        
        
        EquippedItem equippedItem21 = new EquippedItem(character2, EquippedItem.SlotName.valueOf("Head"), 1) ;
        equippedItem21 = equippedItemDao.create(equippedItem21);
        EquippedItem equippedItem22 = new EquippedItem(character2, EquippedItem.SlotName.valueOf("Body"), 2) ;
        equippedItem22 = equippedItemDao.create(equippedItem22);
        EquippedItem equippedItem23 = new EquippedItem(character2, EquippedItem.SlotName.valueOf("Feet"), 3) ;
        equippedItem23 = equippedItemDao.create(equippedItem23);
        EquippedItem equippedItem24 = new EquippedItem(character2, EquippedItem.SlotName.valueOf("Hands"), 4) ;
        equippedItem24 = equippedItemDao.create(equippedItem24);
        EquippedItem equippedItem25 = new EquippedItem(character2, EquippedItem.SlotName.valueOf("MainHand"), 5) ;
        equippedItem25 = equippedItemDao.create(equippedItem25);
        
        EquippedItem equippedItem31 = new EquippedItem(character3, EquippedItem.SlotName.valueOf("Head"), 1) ;
        equippedItem31 = equippedItemDao.create(equippedItem31);
        EquippedItem equippedItem32 = new EquippedItem(character3, EquippedItem.SlotName.valueOf("Body"), 2) ;
        equippedItem32 = equippedItemDao.create(equippedItem32);
        EquippedItem equippedItem33 = new EquippedItem(character3, EquippedItem.SlotName.valueOf("Feet"), 3) ;
        equippedItem33 = equippedItemDao.create(equippedItem33);
        EquippedItem equippedItem34 = new EquippedItem(character3, EquippedItem.SlotName.valueOf("Hands"), 4) ;
        equippedItem34 = equippedItemDao.create(equippedItem34);
        EquippedItem equippedItem35 = new EquippedItem(character3, EquippedItem.SlotName.valueOf("MainHand"), 5) ;
        equippedItem35 = equippedItemDao.create(equippedItem35);
        
        EquippedItem equippedItem41 = new EquippedItem(character4, EquippedItem.SlotName.valueOf("Head"), 1) ;
        equippedItem41 = equippedItemDao.create(equippedItem41);
        EquippedItem equippedItem42 = new EquippedItem(character4, EquippedItem.SlotName.valueOf("Body"), 2) ;
        equippedItem42 = equippedItemDao.create(equippedItem42);
        EquippedItem equippedItem43 = new EquippedItem(character4, EquippedItem.SlotName.valueOf("Feet"), 3) ;
        equippedItem43 = equippedItemDao.create(equippedItem43);
        EquippedItem equippedItem44 = new EquippedItem(character4, EquippedItem.SlotName.valueOf("Hands"), 4) ;
        equippedItem44 = equippedItemDao.create(equippedItem44);
        EquippedItem equippedItem45 = new EquippedItem(character4, EquippedItem.SlotName.valueOf("MainHand"), 5) ;
        equippedItem45 = equippedItemDao.create(equippedItem45);
        
        EquippedItem equippedItem51 = new EquippedItem(character5, EquippedItem.SlotName.valueOf("Head"), 1) ;
        equippedItem51 = equippedItemDao.create(equippedItem51);
        EquippedItem equippedItem52 = new EquippedItem(character5, EquippedItem.SlotName.valueOf("Body"), 2) ;
        equippedItem52 = equippedItemDao.create(equippedItem52);
        EquippedItem equippedItem53 = new EquippedItem(character5, EquippedItem.SlotName.valueOf("Feet"), 3) ;
        equippedItem53 = equippedItemDao.create(equippedItem53);
        EquippedItem equippedItem54 = new EquippedItem(character5, EquippedItem.SlotName.valueOf("Hands"), 4) ;
        equippedItem54 = equippedItemDao.create(equippedItem54);
        EquippedItem equippedItem55 = new EquippedItem(character5, EquippedItem.SlotName.valueOf("MainHand"), 5) ;
        equippedItem55 = equippedItemDao.create(equippedItem55);
        
        
        // Currency
        Currency currency1 = new Currency("Gold", 10000, 1000, true, false);
        currency1 = currencyDao.create(currency1);

        Currency currency2 = new Currency("Silver", 50000, 5000, true, false);
        currency2 = currencyDao.create(currency2);

        Currency currency3 = new Currency("Bronze", 100000, 10000, false, false);
        currency3 = currencyDao.create(currency3);

        Currency currency4 = new Currency("Diamonds", 500, 50, true, true);
        currency4 = currencyDao.create(currency4);

        Currency currency5 = new Currency("Emeralds", 1000, 100, false, true);
        currency5 = currencyDao.create(currency5);
        
        
        // CurrencyItem
        CurrencyItem currencyItem1 = new CurrencyItem(character1, currency1, 500, 50);
        currencyItemDao.create(currencyItem1);

        CurrencyItem currencyItem2 = new CurrencyItem(character2, currency2, 2500, 250);
        currencyItemDao.create(currencyItem2);

        CurrencyItem currencyItem3 = new CurrencyItem(character3, currency3, 5000, 500);
        currencyItemDao.create(currencyItem3);

        CurrencyItem currencyItem4 = new CurrencyItem(character4, currency4, 25, 5);
        currencyItemDao.create(currencyItem4);

        CurrencyItem currencyItem5 = new CurrencyItem(character5, currency5, 50, 10);
        currencyItemDao.create(currencyItem5);
   
        
        // EquipmentBonus
        EquipmentBonus equipmentBonus1 = new EquipmentBonus(gear1, EquipmentBonus.AttributeName.valueOf("Strength"), 10);
        equipmentBonus1 = equipmentBonusDao.create(equipmentBonus1);

        EquipmentBonus equipmentBonus2 = new EquipmentBonus(gear2, EquipmentBonus.AttributeName.valueOf("Dexterity"), 12);
        equipmentBonus2 = equipmentBonusDao.create(equipmentBonus2);

        EquipmentBonus equipmentBonus3 = new EquipmentBonus(gear3, EquipmentBonus.AttributeName.valueOf("Vitality"), 8);
        equipmentBonus3 = equipmentBonusDao.create(equipmentBonus3);

        EquipmentBonus equipmentBonus4 = new EquipmentBonus(weapon1, EquipmentBonus.AttributeName.valueOf("Mind"), 6);
        equipmentBonus4 = equipmentBonusDao.create(equipmentBonus4);

        EquipmentBonus equipmentBonus5 = new EquipmentBonus(weapon2, EquipmentBonus.AttributeName.valueOf("Intelligence"), 14);
        equipmentBonus5 = equipmentBonusDao.create(equipmentBonus5);
        
        //Consumable 
        Consumable consumable1 = new Consumable("Consumable1", 99, 20.0, 5, "Healing Potion");
        consumable1 = consumableDao.create(consumable1);

	     Consumable consumable2 = new Consumable("Consumable2", 99, 20.0, 10, "Mana Elixir");
	     consumable2 = consumableDao.create(consumable2);
	
	     Consumable consumable3 = new Consumable("Consumable3", 99, 20.0, 15, "Strength Tonic");
	     consumable3 = consumableDao.create(consumable3);
	
	     Consumable consumable4 = new Consumable("Consumable4", 99, 20.0, 20, "Dexterity Elixir");
	     consumable4 = consumableDao.create(consumable4);
	
	     Consumable consumable5 = new Consumable("Consumable5", 99, 20.0, 25, "Intellect Potion");
	     consumable5 = consumableDao.create(consumable5);
	     
	     
	     // ConsumableBonus
	     ConsumableBonus consumableBonus1 = new ConsumableBonus(consumable1, ConsumableBonus.AttributeName.valueOf("Strength"), 0.33, 30);
	     consumableBonus1 = consumableBonusDao.create(consumableBonus1);

	     ConsumableBonus consumableBonus2 = new ConsumableBonus(consumable2, ConsumableBonus.AttributeName.valueOf("Dexterity"), 0.16, 40);
	     consumableBonus2 = consumableBonusDao.create(consumableBonus2);

	     ConsumableBonus consumableBonus3 = new ConsumableBonus(consumable3, ConsumableBonus.AttributeName.valueOf("Vitality"), 0.24, 30);
	     consumableBonus3 = consumableBonusDao.create(consumableBonus3);

	     ConsumableBonus consumableBonus4 = new ConsumableBonus(consumable4, ConsumableBonus.AttributeName.valueOf("Mind"), 0.32, 50);
	     consumableBonus4 = consumableBonusDao.create(consumableBonus4);

	     ConsumableBonus consumableBonus5 = new ConsumableBonus(consumable5, ConsumableBonus.AttributeName.valueOf("Intelligence"), 0.27, 20);
	     consumableBonus5 = consumableBonusDao.create(consumableBonus5);
	     
	     
	     // Miscellaneous
	     Miscellaneous miscellaneous1 = new Miscellaneous("Miscellaneous1", 99, 20.0, "Lucky Charm");
	     miscellaneous1 = miscellaneousDao.create(miscellaneous1);

	     Miscellaneous miscellaneous2 = new Miscellaneous("Miscellaneous2", 99, 20.0, "Enchantment Stone");
	     miscellaneous2 = miscellaneousDao.create(miscellaneous2);

	     Miscellaneous miscellaneous3 = new Miscellaneous("Miscellaneous3", 99, 20.0, "Crafting Material");
	     miscellaneous3 = miscellaneousDao.create(miscellaneous3);

	     Miscellaneous miscellaneous4 = new Miscellaneous("Miscellaneous4", 99, 20.0, "Mount Feed");
	     miscellaneous4 = miscellaneousDao.create(miscellaneous4);

	     Miscellaneous miscellaneous5 = new Miscellaneous("Miscellaneous5", 99, 20.0, "Teleport Scroll");
	     miscellaneous5 = miscellaneousDao.create(miscellaneous5);
	     
	     
	     // Inventory
	     Inventory inventory1 = new Inventory(character1, 1, gear1, 1);
	     inventory1 = inventoryDao.create(inventory1);

	     Inventory inventory2 = new Inventory(character1, 2, gear2, 1);
	     inventory2 = inventoryDao.create(inventory2);

	     Inventory inventory3 = new Inventory(character1, 3, weapon1, 1);
	     inventory3 = inventoryDao.create(inventory3);

	     Inventory inventory4 = new Inventory(character1, 4, weapon2, 1);
	     inventory4 = inventoryDao.create(inventory4);

	     Inventory inventory5 = new Inventory(character2, 2, gear3, 1);
	     inventory5 = inventoryDao.create(inventory5);

	     Inventory inventory6 = new Inventory(character2, 3, gear4, 1);
	     inventory6 = inventoryDao.create(inventory6);

	     Inventory inventory7 = new Inventory(character2, 4, gear5, 1);
	     inventory7 = inventoryDao.create(inventory7);

	     Inventory inventory8 = new Inventory(character2, 5, weapon3, 1);
	     inventory8 = inventoryDao.create(inventory8);

	     Inventory inventory9 = new Inventory(character3, 3, weapon4, 1);
	     inventory9 = inventoryDao.create(inventory9);

	     Inventory inventory10 = new Inventory(character4, 4, weapon5, 1);
	     inventory10 = inventoryDao.create(inventory10);

	     Inventory inventory11 = new Inventory(character5, 5, gear5, 1);
	     inventory11 = inventoryDao.create(inventory11);
	     
	     
	     // Add two same consumable to one slot -> should be fine
	     Inventory inventory12 = new Inventory(character5, 6, consumable1, 30);
	     inventory12 = inventoryDao.create(inventory12);
	     
	     Inventory inventory13 = new Inventory(character5, 6, consumable1, 30);
	     inventory13 = inventoryDao.create(inventory13);
	     
	     // Add two same gear to one slot -> should fail, log message on console
	     Inventory inventory14 = new Inventory(character5, 5, gear5, 30);
	     inventory14 = inventoryDao.create(inventory14);
	     
	     
	     
	     // Equipment
	     CustomizedEquipment customizedEquipment1 = new CustomizedEquipment(character1, 1, "Red", CustomizedEquipment.Quality.valueOf("high"), "New");
	     customizedEquipment1 = customizedEquipmentDao.create(customizedEquipment1);

	     CustomizedEquipment customizedEquipment2 = new CustomizedEquipment(character1, 2, "Blue", CustomizedEquipment.Quality.valueOf("high"), "Used");
	     customizedEquipment2 = customizedEquipmentDao.create(customizedEquipment2);

	     CustomizedEquipment customizedEquipment3 = new CustomizedEquipment(character1, 3, "Green", CustomizedEquipment.Quality.valueOf("high"), "Worn-out");
	     customizedEquipment3 = customizedEquipmentDao.create(customizedEquipment3);

	     CustomizedEquipment customizedEquipment4 = new CustomizedEquipment(character2, 1, "Yellow", CustomizedEquipment.Quality.valueOf("normal"), "New");
	     customizedEquipment4 = customizedEquipmentDao.create(customizedEquipment4);

	     CustomizedEquipment customizedEquipment5 = new CustomizedEquipment(character2, 2, "Pink", CustomizedEquipment.Quality.valueOf("normal"), "Used");
	     customizedEquipment5 = customizedEquipmentDao.create(customizedEquipment5);

	     CustomizedEquipment customizedEquipment6 = new CustomizedEquipment(character2, 3, "Green", CustomizedEquipment.Quality.valueOf("normal"), "Worn-out");
	     customizedEquipment6 = customizedEquipmentDao.create(customizedEquipment6);
	     
		
	     
	     
		// ---------- READ. ----------
	    // Job getJobByJobID(int jobID)
	    Job job = jobDao.getJobByJobID(1);
		System.out.format("Reading job by id: jobId:%d jobName:%s \n",
				job.getJobID(), job.getJobName());
		
		// JobLevel getJobLevelByLevelID(int levelID)
		JobLevel jobLevel = jobLevelDao.getJobLevelByLevelID(1);
		System.out.format("Reading jobLevel by id: levelId:%d MinEXP:%d MaxEXP:%d isMaxLevel:%s \n",
				jobLevel.getLevelID(), jobLevel.getMinExp(), jobLevel.getMaxExp(), jobLevel.isMaxLevel());
		
		// JobItems getJobItemsByCharacterIDAndJobID(int characterID, int jobID)
		JobItems jobItems = jobItemsDao.getJobItemsByCharacterIDAndJobID(1, 1);
		System.out.format("Reading JobItems by id: CharacterID:%d JobID:%d LevelID:%d EXP:%d isCurrentJob:%s \n",
				jobItems.getCharacter().getCharacterID(), jobItems.getJob().getJobID(), jobItems.getJobLevel().getLevelID(), jobItems.getExp(), jobItems.isCurrentJob());
		
		// Account getAccountByAccountID(int accountID)
		Account account = accountDao.getAccountByAccountID(1);
		System.out.format("Reading JobItems by id: AccountID:%d Name:%s email:%s  isActive:%s \n",
				account.getAccountID(), account.getName(), account.getEmail(), account.isActive());
		
		// Character getCharacterByCharacterID(int characterID)
		Character c1 = characterDao.getCharacterByCharacterID(1);
		System.out.format("Reading Character by id: CharacterID:%d FirstName:%s LastName:%s\n",
				c1.getCharacterID(), c1.getFirstName(), c1.getLastName());
			
		// getEquipmentBonusByItemCategoryIDAndAttributeName(int itemCategoryID, String attributeName) 
		EquipmentBonus equipmentBonus = equipmentBonusDao.getEquipmentBonusByItemCategoryIDAndAttributeName(1, "Strength");
		System.out.format("Reading EquipmentBonus by ItemCategoryID and AttributeName: ItemCategoryID:%d AttributeName:%s AttributeBonus:%d\n",
				equipmentBonus.getEquipment().getItemCategoryID(), equipmentBonus.getAttributeName(), equipmentBonus.getAttributeBonus());

		
		// getAttributePowerByAttributeNameAndCharacterID(String attributeName, int CharacterID)
		AttributePower attributePower = attributePowerDao.getAttributePowerByAttributeNameAndCharacterID("Strength", 1);
		System.out.format("Reading AttributePower by AttributeName and CharacterID: AttributeName:%s CharacterID:%d AttributePower:%d\n",
				attributePower.getAttributeName(), attributePower.getCharacter().getCharacterID(), attributePower.getAttributePower());

		
		// EquippedItem getEquippedItemBySlotNameAndCharacterID(String slotName, int CharacterID) 
		EquippedItem eI1 = equippedItemDao.getEquippedItemBySlotNameAndCharacterID("MainHand", 1);
		System.out.format("Reading EquippedItem by SlotName and CharacterID: SlotName:%s CharacterID:%d SlotID:%d\n",
				eI1.getSlotName(), eI1.getCharacter().getCharacterID(), eI1.getSlotID());

		
		// Currency getCurrencyByCurrencyName(String currencyName)
		Currency currency = currencyDao.getCurrencyByCurrencyName("Gold");
		System.out.format("Reading Currency by CurrencyName: CurrencyName:%s TotalCapAmount:%d WeeklyCapAmount:%d isWeeklyCap:%s isDiscontinued:%s\n",
		        currency.getCurrencyName(), currency.getTotalCapAmount(), currency.getWeeklyCapAmount(),
		        currency.isWeeklyCap(), currency.isDiscontinued());
		
		// CurrencyItem getCurrencyItemByCharacterIDAndCurrencyName(int characterID, String currencyName) 
		CurrencyItem currencyItem = currencyItemDao.getCurrencyItemByCharacterIDAndCurrencyName(1, "Gold");
		System.out.format("Reading CurrencyItem by CharacterID and CurrencyName: CharacterID:%d CurrencyName:%s TotalAmount:%d WeeklyAmount:%d\n",
		        currencyItem.getCharacter().getCharacterID(), currencyItem.getCurrency().getCurrencyName(), currencyItem.getTotalAmount(), currencyItem.getWeeklyAmount());
		
		// Inventory getInventoryByCharacterIDAndSlotID(int characterID, int slotID)
		Inventory inventory = inventoryDao.getInventoryByCharacterIDAndSlotID(1, 1);
		System.out.format("Reading Inventory by CharacterID and SlotID: CharacterID:%d SlotID:%d ItemCategoryID:%d StackSize:%d\n",
		        inventory.getCharacter().getCharacterID(), inventory.getSlotID(), inventory.getItemCategory().getItemCategoryID(), inventory.getStackSize());

		// CustomizedEquipment getCustomizedEquipmentByOwnerIDAndSlotID(int ownerID, int slotID) 
		CustomizedEquipment customizedEquipment = customizedEquipmentDao.getCustomizedEquipmentByOwnerIDAndSlotID(1, 2);
		Integer makerID = null;
		if (customizedEquipment.getMaker() != null) {
			makerID = customizedEquipment.getMaker().getCharacterID();
		}
		System.out.format("Reading CustomizedEquipment by OwnerID and SlotID: OwnerID:%d SlotID:%d DyeColor:%s Quality:%s Condition:%s MadeByCharacterID:%d\n",
		        customizedEquipment.getOwner().getCharacterID(), customizedEquipment.getSlotID(),
		        customizedEquipment.getDyeColor(), customizedEquipment.getQuality(),
		        customizedEquipment.getCondition(), makerID);
		
		// Consumable getConsumableByItemCategoryID(int itemCategoryID) 
		Consumable consumable = consumableDao.getConsumableByItemCategoryID(11);
		System.out.format("Reading Consumable by ItemCategoryID: ItemCategoryID:%d ItemLevel:%d Description:%s\n",
		        consumable.getItemCategoryID(), consumable.getItemLevel(), consumable.getDescription());

		
		// ConsumableBonus getConsumableBonusByItemCategoryIDAndAttributeName(int itemCategoryID, String attributeName) 
		ConsumableBonus consumableBonus = consumableBonusDao.getConsumableBonusByItemCategoryIDAndAttributeName(11, "Strength");
		System.out.format("Reading ConsumableBonus by ItemCategoryID and AttributeName: ItemCategoryID:%d AttributeName:%s AttributeBonusPercent:%.2f AttributeBonusCap:%d\n",
		        consumableBonus.getConsumable().getItemCategoryID(), consumableBonus.getAttributeName(), consumableBonus.getAttributeBonusPercent(), consumableBonus.getAttributeBonusCap());

		
		// Miscellaneous getMiscellaneousByItemCategoryID(int itemCategoryID) 
		Miscellaneous miscellaneous = miscellaneousDao.getMiscellaneousByItemCategoryID(18);
		System.out.format("Reading Miscellaneous by ItemCategoryID: ItemCategoryID:%d ItemName:%s MaxStackSize:%d VendorPrice:%.2f Description:%s\n",
		        miscellaneous.getItemCategoryID(), miscellaneous.getItemName(), miscellaneous.getMaxStackSize(), miscellaneous.getVendorPrice(), miscellaneous.getDescription());

		
		// Gear getGearByItemCategoryID(int itemCategoryID)
		Gear gear = gearDao.getGearByItemCategoryID(1);
		System.out.format("Reading Gear by ItemCategoryID: ItemCategoryID:%d ItemName:%s MaxStackSize:%d VendorPrice:%.2f DefenceRating:%d MagicDefenceRating:%d\n",
		        gear.getItemCategoryID(), gear.getItemName(), gear.getMaxStackSize(), gear.getVendorPrice(), gear.getDefenceRating(), gear.getMagicDefenceRating());

		
		// Weapon getWeaponByItemCategoryID(int itemCategoryID)
		Weapon weapon = weaponDao.getWeaponByItemCategoryID(6);
		System.out.format("Reading Weapon by ItemCategoryID: ItemCategoryID:%d ItemName:%s MaxStackSize:%d VendorPrice:%.2f DamageDone:%d AutoAttack:%.2f AutoDelay:%.2f\n",
		        weapon.getItemCategoryID(), weapon.getItemName(), weapon.getMaxStackSize(), weapon.getVendorPrice(), weapon.getDamageDone(), weapon.getAutoAttack(), weapon.getAutoDelay());

		
		// GearsForJobs getGearsForJobsByItemCategoryID(int itemCategoryID)
		GearsForJobs gearsForJobs = gearsForJobsDao.getGearsForJobsByItemCategoryID(1);
		System.out.format("Reading GearsForJobs by ItemCategoryID: ItemCategoryID:%d JobID:%d\n",
		        gearsForJobs.getGear().getItemCategoryID(), gearsForJobs.getAssociatedJob().getJobID());


		
		// ---------- Get a list of results. ----------
		List<Character> charactersList = characterDao.getCharactersByLastName("Doe");
		for (Character character : charactersList) {
		    System.out.format("Reading Character by last name: "
		            + "characterID:%d accountID:%d firstName:%s lastName:%s\n",
		            character.getCharacterID(), 
		            character.getAccount().getAccountID(),
		            character.getFirstName(),
		            character.getLastName());
		}

		
	
		// ---------- Update. ----------
		// Character updateCharacterLastName(Character character, String lastName)
		// Character: public Character updateCharacterLastName(Character character, String lastName)
		System.out.format("Previous last name:%s \n", character1.getLastName());
		Character updatedCharacter = characterDao.updateCharacterLastName(character1, "NewLastName");
		System.out.format("Updated last name: "
		        + "characterID:%d accountID:%d firstName:%s lastName:%s\n",
		        updatedCharacter.getCharacterID(),
		        updatedCharacter.getAccount().getAccountID(),
		        updatedCharacter.getFirstName(),
		        updatedCharacter.getLastName());

		
		
		
		// ---------- Delete. ----------
		// Character delete(Character character) 
		characterDao.delete(updatedCharacter);

	}
}
