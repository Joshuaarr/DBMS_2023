
DROP SCHEMA IF EXISTS CS5200Project;
CREATE SCHEMA CS5200Project;
USE CS5200Project;

-- Creating tables
CREATE TABLE `Account` (
    AccountID INT AUTO_INCREMENT,
    `name` VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    isActive BOOLEAN,
    CONSTRAINT pk_Account_AccountID PRIMARY KEY (AccountID)
);

    
CREATE TABLE `Character` (
    CharacterID INT AUTO_INCREMENT,
    AccountID INT,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    CONSTRAINT uq_Character_FirstName_LastName UNIQUE (FirstName, LastName),
    CONSTRAINT pk_Character_CharacterID PRIMARY KEY(CharacterID),
	CONSTRAINT fk_Character_AccountID FOREIGN KEY (AccountID) 
    REFERENCES `Account` (AccountID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE AttributePower (
	AttributeName enum('Strength', 'Dexterity', 'Vitality', 'Mind','Intelligence'),
    CharacterID INT,
    AttributePower INT,
    CONSTRAINT pk_AttributePower_AttributeName_CharacterID PRIMARY KEY (AttributeName,CharacterID),
    -- CONSTRAINT fk_AttributePower_AttributeName FOREIGN KEY (AttributeName) REFERENCES Attribute (AttributeName) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_AttributePower_CharacterID FOREIGN KEY (CharacterID) REFERENCES `Character` (CharacterID) ON DELETE CASCADE ON UPDATE CASCADE
);
    
CREATE TABLE EquippedItem (
    CharacterID INT,
    SlotName enum('MainHand', 'Head', 'Body', 'Hands', 'Feet', 'OffHand', 'Earring', 'Wrist', 'Ring'),
    SlotID INT,
    CONSTRAINT pk_EquippedItem_CharacterID_SlotName PRIMARY KEY (CharacterID, SlotName),
    CONSTRAINT fk_EquippedItem_CharacterID FOREIGN KEY (CharacterID) REFERENCES `Character`(CharacterID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Currency (
    CurrencyName VARCHAR(255),
    TotalCapAmount INT,
    WeeklyCapAmount INT,
    isWeeklyCap BOOLEAN,
    isDiscontinued BOOLEAN,
	CONSTRAINT pk_Currency_CurrencyName PRIMARY KEY (CurrencyName)
);

CREATE TABLE CurrencyItem (
    CharacterID INT,
    CurrencyName VARCHAR(255),
    TotalAmount INT,
    WeeklyAmount INT,
    CONSTRAINT pk_CurrencyItem_CharacterID_CurrencyName PRIMARY KEY (CharacterID, CurrencyName),
    CONSTRAINT fk_CurrencyItem_CharacterID FOREIGN KEY (CharacterID) REFERENCES `Character`(CharacterID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_CurrencyItem_CurrencyName FOREIGN KEY (CurrencyName) REFERENCES Currency(CurrencyName) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ItemCategory (
    ItemCategoryID INT AUTO_INCREMENT,
    ItemName VARCHAR(255),
    MaxStackSize INT,
    VendorPrice DECIMAL(10,2),
    CONSTRAINT pk_ItemCategory_ItemCategoryID PRIMARY KEY (ItemCategoryID)
);

CREATE TABLE Equipment (
    ItemCategoryID INT,
    ItemLevel INT,
    RequiredLevel INT,
    CONSTRAINT pk_Equipment_ItemCategoryID PRIMARY KEY (ItemCategoryID),
    CONSTRAINT fk_Equipment_ItemCategoryID FOREIGN KEY (ItemCategoryID) REFERENCES ItemCategory(ItemCategoryID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Equipment Bonus table
CREATE TABLE EquipmentBonus (
    ItemCategoryID INT,
    AttributeName enum('Strength', 'Dexterity', 'Vitality', 'Mind','Intelligence'),
    AttributeBonus INT,
    CONSTRAINT pk_EquipmentBonus_ItemCategoryID_AttributeName PRIMARY KEY (ItemCategoryID, AttributeName),
    CONSTRAINT fk_EquipmentBonus_ItemCategoryID FOREIGN KEY (ItemCategoryID) REFERENCES Equipment(ItemCategoryID) ON DELETE CASCADE ON UPDATE CASCADE 
    );
    
    -- Consumable table
CREATE TABLE Consumable (
    ItemCategoryID INT,
    ItemLevel INT,
    Description VARCHAR(255),
    CONSTRAINT pk_Consumable_ItemCategoryID PRIMARY KEY (ItemCategoryID),
    CONSTRAINT fk_Consumable_ItemCategoryID Foreign Key (ItemCategoryID) REFERENCES ItemCategory (ItemCategoryID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ConsumableBonus (
    ItemCategoryID INT,
    AttributeName enum('Strength', 'Dexterity', 'Vitality', 'Mind','Intelligence'),
    AttributeBonusPercent DECIMAL(5, 2),
    AttributeBonusCap INT,
    CONSTRAINT pk_ConsumableBonus_ItemCategoryID_AttributeName PRIMARY KEY (ItemCategoryID, AttributeName),
    CONSTRAINT fk_ConsumableBonus_ItemCategoryID FOREIGN KEY (ItemCategoryID) REFERENCES Consumable(ItemCategoryID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Miscellaneous table
CREATE TABLE Miscellaneous (
    ItemCategoryID INT,
    Description VARCHAR(255),
    CONSTRAINT pk_Miscellaneous_ItemCategoryID PRIMARY KEY (ItemCategoryID),
    CONSTRAINT fk_Miscellaneous_ItemCategoryID FOREIGN KEY (ItemCategoryID) REFERENCES ItemCategory (ItemCategoryID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Job Table
CREATE TABLE Job (
    JobID INT AUTO_INCREMENT,
    JobName VARCHAR(255),
    CONSTRAINT pk_Job_JobID PRIMARY KEY (JobID)
);

-- Job Level Table
CREATE TABLE JobLevel (
    LevelID INT AUTO_INCREMENT,
    MinEXP INT,
    MaxEXP INT,
    isMaxLevel BOOLEAN,
	CONSTRAINT pk_JobLevel_LevelID PRIMARY KEY (LevelID)
);

-- Job Items Table
CREATE TABLE JobItems (
    CharacterID INT,
    JobID INT,
    EXP INT,
    LevelID INT,
    isCurrentJob BOOLEAN,
    CONSTRAINT pk_JobItems_CharacterID_JobID PRIMARY KEY (CharacterID, JobID),
    CONSTRAINT fk_JobItems_CharacterID FOREIGN KEY (CharacterID) REFERENCES `Character` (CharacterID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_JobItems_JobID FOREIGN KEY (JobID) REFERENCES Job(JobID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_JobItems_LevelID FOREIGN KEY (LevelID) REFERENCES JobLevel(LevelID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Gear Table
CREATE TABLE Gear (
    ItemCategoryID INT,
    DefenceRating INT,
    MagicDefenceRating INT,
    CONSTRAINT pk_Gear_ItemCategoryID PRIMARY KEY (ItemCategoryID),
	CONSTRAINT fk_Gear_ItemCategoryID FOREIGN KEY (ItemCategoryID) REFERENCES ItemCategory(ItemCategoryID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Gears for Jobs Table
CREATE TABLE GearsForJobs (
    ItemCategoryID INT,
    JobID INT,
    CONSTRAINT pk_GearsForJobs_ItemCategoryID_JobID PRIMARY KEY(ItemCategoryID, JobID),
    CONSTRAINT fk_GearsForJobs_ItemCategoryID FOREIGN KEY (ItemCategoryID) REFERENCES ItemCategory(ItemCategoryID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_GearsForJobs_JobID FOREIGN KEY (JobID) REFERENCES Job(JobID) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Weapon Table
CREATE TABLE Weapon (
    ItemCategoryID INT,
    DamageDone INT,
    AutoAttack DECIMAL(5,2),
    AutoDelay DECIMAL(5,2),
    AssociatedJobID INT,
    CONSTRAINT pk_Weapon_ItemCategoryID PRIMARY KEY (ItemCategoryID),
    CONSTRAINT fk_Weapon_AssociatedJobID FOREIGN KEY (AssociatedJobID) REFERENCES Job (JobID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_Weapon_ItemCategoryID FOREIGN KEY (ItemCategoryID) REFERENCES ItemCategory(ItemCategoryID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Inventory table
CREATE TABLE Inventory (
    CharacterID INT,
    SlotID INT,
    ItemCategoryID INT,
    StackSize INT,
    CONSTRAINT pk_Inventory_CharacterID_SlotID PRIMARY KEY (CharacterID, SlotID),
    CONSTRAINT fk_Inventory_CharacterID FOREIGN KEY (CharacterID) REFERENCES `Character`(CharacterID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_Inventory_ItemCategoryID FOREIGN KEY (ItemCategoryID) REFERENCES ItemCategory(ItemCategoryID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Customized Equipment Table
CREATE TABLE CustomizedEquipment (
    CharacterID INT,
    SlotID INT,
    DyeColor VARCHAR(255),
    Quality enum('high', 'normal'),
    `Condition` VARCHAR(255),
    MadeByCharacterID INT,
    CONSTRAINT pk_CustomizedEquipment_CharacterID_SlotID  PRIMARY KEY (CharacterID, SlotID),
	CONSTRAINT fk_CustomizedEquipment_CharacterID FOREIGN KEY (CharacterID) REFERENCES `Character`(CharacterID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_CustomizedEquipment_MadeByCharacterID FOREIGN KEY (MadeByCharacterID) REFERENCES `Character`(CharacterID) ON DELETE CASCADE ON UPDATE CASCADE
);

       
INSERT INTO `Account` (`name`, `email`, `isActive`)
VALUES
    ('account1', 'account1@example.com', 1),
    ('account2', 'account2@example.com', 1),
    ('account3', 'account3@example.com', 1),
    ('account4', 'account4@example.com', 1),
    ('account5', 'account5@example.com', 1),
    ('account6', 'account6@example.com', 1),
    ('account7', 'account7@example.com', 1),
    ('account8', 'account8@example.com', 1),
    ('account9', 'account9@example.com', 1),
    ('account10', 'account10@example.com', 1);
    
INSERT INTO `Character` (`AccountID`, `FirstName`, `LastName`)
VALUES
    (1, 'FirstName1', 'LastName1'),
    (2, 'FirstName2', 'LastName2'),
    (3, 'FirstName3', 'LastName3'),
    (4, 'FirstName4', 'LastName4'),
    (5, 'FirstName5', 'LastName5'),
    (6, 'FirstName6', 'LastName6'),
    (7, 'FirstName7', 'LastName7'),
    (8, 'FirstName8', 'LastName8'),
    (9, 'FirstName9', 'LastName9'),
    (10, 'FirstName10', 'LastName10');

-- Insert 10 records into AttributePower
INSERT INTO AttributePower (AttributeName, CharacterID, AttributePower)
VALUES
    ('Strength', 1, 100),
    ('Dexterity', 2, 90),
    ('Vitality', 3, 80),
    ('Mind', 4, 70),
    ('Intelligence', 5, 60),
    ('Strength', 6, 110),
    ('Dexterity', 7, 95),
    ('Vitality', 8, 85),
    ('Mind', 9, 75),
    ('Intelligence', 10, 65);

-- Insert 10 records into EquippedItem
INSERT INTO EquippedItem (CharacterID, SlotName, SlotID)
VALUES
    (1, 'MainHand', 1),
    (1, 'Head', 2),
    (2, 'MainHand', 3),
    (2, 'OffHand', 4),
    (3, 'Head', 5),
    (3, 'Hands', 6),
    (4, 'MainHand', 7),
    (4, 'Body', 8),
    (5, 'Head', 9),
    (5, 'Feet', 10);

-- Insert 10 records into Currency
INSERT INTO Currency (CurrencyName, TotalCapAmount, WeeklyCapAmount, isWeeklyCap, isDiscontinued)
VALUES
    ('Gold', 10000, 1000, 1, 0),
    ('Silver', 50000, 5000, 1, 0),
    ('Copper', 100000, 10000, 1, 0),
    ('Platinum', 1000, 100, 1, 0),
    ('Diamond', 500, 50, 1, 0),
    ('Ruby', 200, 20, 1, 0),
    ('Emerald', 100, 10, 1, 0),
    ('Sapphire', 50, 5, 1, 0),
    ('Topaz', 20, 2, 1, 0),
    ('Amethyst', 10, 1, 1, 0);

-- Insert 10 records into CurrencyItem
INSERT INTO CurrencyItem (CharacterID, CurrencyName, TotalAmount, WeeklyAmount)
VALUES
    (1, 'Gold', 5000, 500),
    (1, 'Silver', 10000, 1000),
    (2, 'Gold', 6000, 600),
    (2, 'Silver', 12000, 1200),
    (3, 'Gold', 7000, 700),
    (3, 'Silver', 14000, 1400),
    (4, 'Gold', 8000, 800),
    (4, 'Silver', 16000, 1600),
    (5, 'Gold', 9000, 900),
    (5, 'Silver', 18000, 1800);

-- Insert 10 records into ItemCategory
INSERT INTO ItemCategory (ItemName, MaxStackSize, VendorPrice)
VALUES
    ('Item1', 10, 10.00),
    ('Item2', 5, 20.00),
    ('Item3', 20, 5.00),
    ('Item4', 15, 15.00),
    ('Item5', 25, 25.00),
    ('Item6', 30, 30.00),
    ('Item7', 8, 12.00),
    ('Item8', 12, 8.00),
    ('Item9', 18, 18.00),
    ('Item10', 7, 14.00);

-- Insert 10 records into Equipment
INSERT INTO Equipment (ItemCategoryID, ItemLevel, RequiredLevel)
VALUES
    (1, 10, 5),
    (2, 15, 8),
    (3, 20, 10),
    (4, 25, 12),
    (5, 30, 15),
    (6, 35, 18),
    (7, 10, 5),
    (8, 15, 8),
    (9, 20, 10),
    (10, 25, 12);

-- Insert 10 records into EquipmentBonus
INSERT INTO EquipmentBonus (ItemCategoryID, AttributeName, AttributeBonus)
VALUES
    (1, 'Strength', 5),
    (2, 'Dexterity', 4),
    (3, 'Vitality', 3),
    (4, 'Mind', 2),
    (5, 'Intelligence', 1),
    (6, 'Strength', 6),
    (7, 'Dexterity', 5),
    (8, 'Vitality', 4),
    (9, 'Mind', 3),
    (10, 'Intelligence', 2);

-- Insert 10 records into Consumable
INSERT INTO Consumable (ItemCategoryID, ItemLevel, Description)
VALUES
    (1, 10, 'Health Potion'),
    (2, 15, 'Mana Elixir'),
    (3, 20, 'Stamina Boost'),
    (4, 25, 'Strength Potion'),
    (5, 30, 'Agility Tonic'),
    (6, 35, 'Intelligence Elixir'),
    (7, 10, 'Health Potion'),
    (8, 15, 'Mana Elixir'),
    (9, 20, 'Stamina Boost'),
    (10, 25, 'Strength Potion');

-- Insert 10 records into ConsumableBonus
INSERT INTO ConsumableBonus (ItemCategoryID, AttributeName, AttributeBonusPercent, AttributeBonusCap)
VALUES
    (1, 'Strength', 10.0, 20),
    (2, 'Dexterity', 8.0, 16),
    (3, 'Vitality', 6.0, 12),
    (4, 'Mind', 4.0, 8),
    (5, 'Intelligence', 2.0, 4),
    (6, 'Strength', 12.0, 24),
    (7, 'Dexterity', 10.0, 20),
    (8, 'Vitality', 8.0, 16),
    (9, 'Mind', 6.0, 12),
    (10, 'Intelligence', 4.0, 8);
    
select * from ConsumableBonus;

INSERT INTO Job (JobName) VALUES
('Warrior'),
('Mage'),
('Ranger'),
('Paladin'),
('Thief'),
('Berserker'),
('Priest'),
('Druid'),
('Necromancer'),
('Monk');

INSERT INTO JobLevel (MinEXP, MaxEXP, isMaxLevel) VALUES
(0, 1000, FALSE),
(1001, 3000, FALSE),
(3001, 6000, FALSE),
(6001, 10000, FALSE),
(10001, 15000, FALSE),
(15001, 21000, FALSE),
(21001, 28000, FALSE),
(28001, 36000, FALSE),
(36001, 45000, FALSE),
(45001, 55000, TRUE);

INSERT INTO JobItems (CharacterID, JobID, EXP, LevelID, isCurrentJob) VALUES
(1, 1, 500, 1, TRUE),
(2, 2, 1500, 2, TRUE),
(3, 3, 2500, 3, TRUE),
(4, 4, 3500, 4, TRUE),
(5, 5, 5000, 5, TRUE),
(6, 6, 6500, 6, TRUE),
(7, 7, 8000, 7, TRUE),
(8, 8, 9500, 8, TRUE),
(9, 9, 11000, 9, TRUE),
(10, 10, 12500, 10, TRUE);

INSERT INTO Gear (ItemCategoryID, DefenceRating, MagicDefenceRating) VALUES
(1, 50, 30),
(2, 45, 35),
(3, 40, 40),
(4, 35, 45),
(5, 30, 50),
(6, 25, 55),
(7, 20, 60),
(8, 15, 65),
(9, 10, 70),
(10, 5, 75);

INSERT INTO GearsForJobs (ItemCategoryID, JobID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

INSERT INTO Weapon (ItemCategoryID, DamageDone, AutoAttack, AutoDelay, AssociatedJobID) VALUES
(1, 100, 1.50, 2.50, 1),
(2, 90, 1.40, 2.40, 2),
(3, 80, 1.30, 2.30, 3),
(4, 70, 1.20, 2.20, 4),
(5, 60, 1.10, 2.10, 5),
(6, 50, 1.00, 2.00, 6),
(7, 40, 0.90, 1.90, 7),
(8, 30, 0.80, 1.80, 8),
(9, 20, 0.70, 1.70, 9),
(10, 10, 0.60, 1.60, 10);

INSERT INTO Inventory (CharacterID, SlotID, ItemCategoryID, StackSize) VALUES
(1, 1, 1, 10),
(2, 2, 2, 20),
(3, 3, 3, 30),
(4, 4, 4, 40),
(5, 5, 5, 50),
(6, 6, 6, 60),
(7, 7, 7, 70),
(8, 8, 8, 80),
(9, 9, 9, 90),
(10, 10, 10, 100);
