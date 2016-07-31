-- MySQL dump 10.13  Distrib 5.6.28, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: BHP_BACK_OFFICE
-- ------------------------------------------------------
-- Server version	5.6.28-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ADDRESS`
--

DROP TABLE IF EXISTS `ADDRESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADDRESS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADRS_1` varchar(100) DEFAULT NULL,
  `ADRS_2` varchar(100) DEFAULT NULL,
  `STREET` varchar(100) DEFAULT NULL,
  `ZIP_CODE` bigint(20) DEFAULT NULL,
  `CITY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_4wweqmwuhbl7k26sf926pwkxx` (`CITY_ID`),
  CONSTRAINT `FK_4wweqmwuhbl7k26sf926pwkxx` FOREIGN KEY (`CITY_ID`) REFERENCES `CITY` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BANK_ACCOUNT`
--

DROP TABLE IF EXISTS `BANK_ACCOUNT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BANK_ACCOUNT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IFSC_CODE` varchar(25) DEFAULT NULL,
  `ACC_HOLD_NAME` varchar(50) DEFAULT NULL,
  `ACC_NO` varchar(25) DEFAULT NULL,
  `BNK_NAME` varchar(50) DEFAULT NULL,
  `BRANCH` varchar(50) DEFAULT NULL,
  `BNK_ADRS_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_i5cagumwv1eqv23nto27nyiw8` (`BNK_ADRS_ID`),
  CONSTRAINT `FK_i5cagumwv1eqv23nto27nyiw8` FOREIGN KEY (`BNK_ADRS_ID`) REFERENCES `ADDRESS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CANDIDATE`
--

DROP TABLE IF EXISTS `CANDIDATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CANDIDATE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRTD_DATE` datetime DEFAULT NULL,
  `IS_DLTD` tinyint(1) NOT NULL DEFAULT '0',
  `DLTD_DATE` datetime DEFAULT NULL,
  `UPTD_DATE` datetime DEFAULT NULL,
  `CAND_ID` varchar(15) NOT NULL,
  `CHLD_NAME` varchar(50) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `DOJ` date DEFAULT NULL,
  `IS_EMP` tinyint(1) NOT NULL DEFAULT '0',
  `FAM_EMAIL` varchar(50) DEFAULT NULL,
  `FATR_NAME` varchar(50) DEFAULT NULL,
  `FRST_NAME` varchar(50) DEFAULT NULL,
  `LST_NAME` varchar(50) DEFAULT NULL,
  `MDLE_NAME` varchar(50) DEFAULT NULL,
  `MOTR_NAME` varchar(50) DEFAULT NULL,
  `IS_OFR_LTR_GNRTD` tinyint(1) NOT NULL DEFAULT '0',
  `OFC_EMAIL` varchar(50) DEFAULT NULL,
  `PARTNER` varchar(50) DEFAULT NULL,
  `PER_EMAIL` varchar(50) DEFAULT NULL,
  `PRIR_EXP_MNT` int(11) DEFAULT NULL,
  `PRIR_EXP_YR` int(11) DEFAULT NULL,
  `SRCD_BY` varchar(50) DEFAULT NULL,
  `SPOS_NAME` varchar(50) DEFAULT NULL,
  `CRTD_BY_ID` bigint(20) DEFAULT NULL,
  `DLTD_BY_ID` bigint(20) DEFAULT NULL,
  `UPTD_BY_ID` bigint(20) DEFAULT NULL,
  `BNK_ACC_ID` bigint(20) DEFAULT NULL,
  `BLD_GRP_ID` bigint(20) DEFAULT NULL,
  `CLNT_ID` bigint(20) DEFAULT NULL,
  `CNTY_OF_ORG_ID` bigint(20) DEFAULT NULL,
  `CNTY_TO_VST_ID` bigint(20) DEFAULT NULL,
  `CURR_ADRS_ID` bigint(20) DEFAULT NULL,
  `DSGNT_ID` bigint(20) DEFAULT NULL,
  `DVSN_ID` bigint(20) DEFAULT NULL,
  `EMP_STS_ID` bigint(20) DEFAULT NULL,
  `FAM_CONT_NO1_ID` bigint(20) DEFAULT NULL,
  `FAM_CONT_NO2_ID` bigint(20) DEFAULT NULL,
  `GNDR_ID` bigint(20) NOT NULL,
  `OFC_CONT_NO_ID` bigint(20) DEFAULT NULL,
  `OFC_DTLS_ID` bigint(20) DEFAULT NULL,
  `ONST_ADRS_ID` bigint(20) DEFAULT NULL,
  `ONST_CONT_NO_ID` bigint(20) DEFAULT NULL,
  `PASSPORT_ID` bigint(20) DEFAULT NULL,
  `PERM_ADRS_ID` bigint(20) DEFAULT NULL,
  `PER_CONT_NO_ID` bigint(20) DEFAULT NULL,
  `PRPUS_ID` bigint(20) DEFAULT NULL,
  `REG_STS_ID` bigint(20) DEFAULT NULL,
  `EMP_SALARY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_ld4mdvh446hx0spm0a3o9vpnh` (`CAND_ID`),
  KEY `FK_91pvmq2ot5iwrldhuac9w7js4` (`CRTD_BY_ID`),
  KEY `FK_mqql4h6odaiutsw86d9finl23` (`DLTD_BY_ID`),
  KEY `FK_kg31fmltx4yyi5oti03suw7hh` (`UPTD_BY_ID`),
  KEY `FK_bfs1xdudmcg15jqjts09rvwv7` (`BNK_ACC_ID`),
  KEY `FK_2999a2snpq5411a7wp0gb2pxq` (`BLD_GRP_ID`),
  KEY `FK_5w146opq9aygmcabyryb2oh2` (`CLNT_ID`),
  KEY `FK_366i503kaig4h9uuueddndl40` (`CNTY_OF_ORG_ID`),
  KEY `FK_dcnidr2rsuk3nmbjp8v5x78m1` (`CNTY_TO_VST_ID`),
  KEY `FK_d5e9ia7akl0gkpyx928f9u0d4` (`CURR_ADRS_ID`),
  KEY `FK_a3u5yypcykou5fymkuiy7uodi` (`DSGNT_ID`),
  KEY `FK_erkutbvvlg88b6rpk59jy0qa5` (`DVSN_ID`),
  KEY `FK_dvao94n65njxk8n8w7j9xif1x` (`EMP_STS_ID`),
  KEY `FK_24erwf074iu8wwbwschr7oaaw` (`FAM_CONT_NO1_ID`),
  KEY `FK_5ohv0hhsi4amjt6tu072byc85` (`FAM_CONT_NO2_ID`),
  KEY `FK_oikhb2534336fr8qs4saik0rd` (`GNDR_ID`),
  KEY `FK_berea816lnuyeeef38p6cpr99` (`OFC_CONT_NO_ID`),
  KEY `FK_1db9tyrg949xhwpr6nigm12m8` (`OFC_DTLS_ID`),
  KEY `FK_iw8m9o1nsr2tn4f2oe5sj6aj` (`ONST_ADRS_ID`),
  KEY `FK_3cadk93lx32ircxai42yd3qbe` (`ONST_CONT_NO_ID`),
  KEY `FK_18ntm0v2f834gf4ap9mcko5rt` (`PASSPORT_ID`),
  KEY `FK_nnjuwk1c759x2ut6ekq7kquk2` (`PERM_ADRS_ID`),
  KEY `FK_8fr08h5fxs5qduwbi8wtlm9m6` (`PER_CONT_NO_ID`),
  KEY `FK_df941xc00o2wd0e02ujwbbml5` (`PRPUS_ID`),
  KEY `FK_l4hov6q0ioip6wj6507pj9lov` (`REG_STS_ID`),
  KEY `FK_fg5lbgo6wapx4bwh29td5d90h` (`EMP_SALARY_ID`),
  CONSTRAINT `FK_18ntm0v2f834gf4ap9mcko5rt` FOREIGN KEY (`PASSPORT_ID`) REFERENCES `PASSPORT` (`ID`),
  CONSTRAINT `FK_1db9tyrg949xhwpr6nigm12m8` FOREIGN KEY (`OFC_DTLS_ID`) REFERENCES `OFFICIAL_CARDS` (`ID`),
  CONSTRAINT `FK_24erwf074iu8wwbwschr7oaaw` FOREIGN KEY (`FAM_CONT_NO1_ID`) REFERENCES `PHONE` (`ID`),
  CONSTRAINT `FK_2999a2snpq5411a7wp0gb2pxq` FOREIGN KEY (`BLD_GRP_ID`) REFERENCES `LOOKUP_DTLS` (`ID`),
  CONSTRAINT `FK_366i503kaig4h9uuueddndl40` FOREIGN KEY (`CNTY_OF_ORG_ID`) REFERENCES `COUNTRY` (`ID`),
  CONSTRAINT `FK_3cadk93lx32ircxai42yd3qbe` FOREIGN KEY (`ONST_CONT_NO_ID`) REFERENCES `PHONE` (`ID`),
  CONSTRAINT `FK_5ohv0hhsi4amjt6tu072byc85` FOREIGN KEY (`FAM_CONT_NO2_ID`) REFERENCES `PHONE` (`ID`),
  CONSTRAINT `FK_5w146opq9aygmcabyryb2oh2` FOREIGN KEY (`CLNT_ID`) REFERENCES `CLIENT` (`ID`),
  CONSTRAINT `FK_8fr08h5fxs5qduwbi8wtlm9m6` FOREIGN KEY (`PER_CONT_NO_ID`) REFERENCES `PHONE` (`ID`),
  CONSTRAINT `FK_91pvmq2ot5iwrldhuac9w7js4` FOREIGN KEY (`CRTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_a3u5yypcykou5fymkuiy7uodi` FOREIGN KEY (`DSGNT_ID`) REFERENCES `LOOKUP_DTLS` (`ID`),
  CONSTRAINT `FK_berea816lnuyeeef38p6cpr99` FOREIGN KEY (`OFC_CONT_NO_ID`) REFERENCES `PHONE` (`ID`),
  CONSTRAINT `FK_bfs1xdudmcg15jqjts09rvwv7` FOREIGN KEY (`BNK_ACC_ID`) REFERENCES `BANK_ACCOUNT` (`ID`),
  CONSTRAINT `FK_d5e9ia7akl0gkpyx928f9u0d4` FOREIGN KEY (`CURR_ADRS_ID`) REFERENCES `ADDRESS` (`ID`),
  CONSTRAINT `FK_dcnidr2rsuk3nmbjp8v5x78m1` FOREIGN KEY (`CNTY_TO_VST_ID`) REFERENCES `COUNTRY` (`ID`),
  CONSTRAINT `FK_df941xc00o2wd0e02ujwbbml5` FOREIGN KEY (`PRPUS_ID`) REFERENCES `LOOKUP_DTLS` (`ID`),
  CONSTRAINT `FK_dvao94n65njxk8n8w7j9xif1x` FOREIGN KEY (`EMP_STS_ID`) REFERENCES `LOOKUP_DTLS` (`ID`),
  CONSTRAINT `FK_erkutbvvlg88b6rpk59jy0qa5` FOREIGN KEY (`DVSN_ID`) REFERENCES `LOOKUP_DTLS` (`ID`),
  CONSTRAINT `FK_fg5lbgo6wapx4bwh29td5d90h` FOREIGN KEY (`EMP_SALARY_ID`) REFERENCES `EMPLOYEE_SALARY` (`ID`),
  CONSTRAINT `FK_iw8m9o1nsr2tn4f2oe5sj6aj` FOREIGN KEY (`ONST_ADRS_ID`) REFERENCES `ADDRESS` (`ID`),
  CONSTRAINT `FK_kg31fmltx4yyi5oti03suw7hh` FOREIGN KEY (`UPTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_l4hov6q0ioip6wj6507pj9lov` FOREIGN KEY (`REG_STS_ID`) REFERENCES `LOOKUP_DTLS` (`ID`),
  CONSTRAINT `FK_mqql4h6odaiutsw86d9finl23` FOREIGN KEY (`DLTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_nnjuwk1c759x2ut6ekq7kquk2` FOREIGN KEY (`PERM_ADRS_ID`) REFERENCES `ADDRESS` (`ID`),
  CONSTRAINT `FK_oikhb2534336fr8qs4saik0rd` FOREIGN KEY (`GNDR_ID`) REFERENCES `LOOKUP_DTLS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CANDIDATE_SEQUENCE`
--

DROP TABLE IF EXISTS `CANDIDATE_SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CANDIDATE_SEQUENCE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRTD_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CANDIDATE_SKILL`
--

DROP TABLE IF EXISTS `CANDIDATE_SKILL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CANDIDATE_SKILL` (
  `CANDIDATE_ID` bigint(20) NOT NULL,
  `skillSet_ID` bigint(20) NOT NULL,
  UNIQUE KEY `UK_8rxyii19m96gs5oex9ovp9dpj` (`skillSet_ID`),
  KEY `FK_di0dgr9mlwgwyf6bkix7ikqpi` (`CANDIDATE_ID`),
  CONSTRAINT `FK_8rxyii19m96gs5oex9ovp9dpj` FOREIGN KEY (`skillSet_ID`) REFERENCES `SKILL` (`ID`),
  CONSTRAINT `FK_di0dgr9mlwgwyf6bkix7ikqpi` FOREIGN KEY (`CANDIDATE_ID`) REFERENCES `CANDIDATE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CITY`
--

DROP TABLE IF EXISTS `CITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CITY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(10) DEFAULT NULL,
  `DESC` varchar(50) DEFAULT NULL,
  `STATE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_fffmjx3htpjud7o5h92ejpsnt` (`STATE_ID`),
  CONSTRAINT `FK_fffmjx3htpjud7o5h92ejpsnt` FOREIGN KEY (`STATE_ID`) REFERENCES `STATE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=47575 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CLIENT`
--

DROP TABLE IF EXISTS `CLIENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CLIENT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRTD_DATE` datetime DEFAULT NULL,
  `IS_DLTD` tinyint(1) NOT NULL DEFAULT '0',
  `DLTD_DATE` datetime DEFAULT NULL,
  `UPTD_DATE` datetime DEFAULT NULL,
  `CLNT_ID` varchar(15) NOT NULL,
  `CLNT_NAME` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `REVENUE` float DEFAULT NULL,
  `WEB_URL` varchar(255) DEFAULT NULL,
  `CRTD_BY_ID` bigint(20) DEFAULT NULL,
  `DLTD_BY_ID` bigint(20) DEFAULT NULL,
  `UPTD_BY_ID` bigint(20) DEFAULT NULL,
  `ACC_MNGR_ID` bigint(20) DEFAULT NULL,
  `BUS_UNT_ID` bigint(20) DEFAULT NULL,
  `BUS_DEV_MNGR_ID` bigint(20) DEFAULT NULL,
  `BUS_UNT_HED_ID` bigint(20) DEFAULT NULL,
  `CLNT_ADRS_ID` bigint(20) DEFAULT NULL,
  `CLNT_STS_ID` bigint(20) DEFAULT NULL,
  `CONT_NO_ID` bigint(20) DEFAULT NULL,
  `PONT_OF_CONT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_se39m4bj6yhmrur3e8iyijcm2` (`CLNT_ID`),
  KEY `FK_3niyjn9bqp9h8x3fqxo50wlnx` (`CRTD_BY_ID`),
  KEY `FK_tledabpr2xnlm59veqt5ptbd5` (`DLTD_BY_ID`),
  KEY `FK_eswg2ncfkfy933p5iwp1cxglw` (`UPTD_BY_ID`),
  KEY `FK_tgeiib9hk6vw5ys2pt884vxqp` (`ACC_MNGR_ID`),
  KEY `FK_hdw1clgmqtc5724owat6lrdvs` (`BUS_UNT_ID`),
  KEY `FK_dostbvvlk6ec1rp7vdcmedx57` (`BUS_DEV_MNGR_ID`),
  KEY `FK_2nhfwh1gxv4d58qf0q7m2pf25` (`BUS_UNT_HED_ID`),
  KEY `FK_du6dqd7g8w558gl37foxb3vf8` (`CLNT_ADRS_ID`),
  KEY `FK_dtefq4b6xp1w6c0qb1ybe0egp` (`CLNT_STS_ID`),
  KEY `FK_3jud5iaa51u4598056q7wvrc6` (`CONT_NO_ID`),
  KEY `FK_qscr98vmo3xgcixylek1gmfj0` (`PONT_OF_CONT_ID`),
  CONSTRAINT `FK_2nhfwh1gxv4d58qf0q7m2pf25` FOREIGN KEY (`BUS_UNT_HED_ID`) REFERENCES `EMPLOYEE` (`ID`),
  CONSTRAINT `FK_3jud5iaa51u4598056q7wvrc6` FOREIGN KEY (`CONT_NO_ID`) REFERENCES `PHONE` (`ID`),
  CONSTRAINT `FK_3niyjn9bqp9h8x3fqxo50wlnx` FOREIGN KEY (`CRTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_dostbvvlk6ec1rp7vdcmedx57` FOREIGN KEY (`BUS_DEV_MNGR_ID`) REFERENCES `EMPLOYEE` (`ID`),
  CONSTRAINT `FK_dtefq4b6xp1w6c0qb1ybe0egp` FOREIGN KEY (`CLNT_STS_ID`) REFERENCES `LOOKUP_DTLS` (`ID`),
  CONSTRAINT `FK_du6dqd7g8w558gl37foxb3vf8` FOREIGN KEY (`CLNT_ADRS_ID`) REFERENCES `ADDRESS` (`ID`),
  CONSTRAINT `FK_eswg2ncfkfy933p5iwp1cxglw` FOREIGN KEY (`UPTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_hdw1clgmqtc5724owat6lrdvs` FOREIGN KEY (`BUS_UNT_ID`) REFERENCES `LOOKUP_DTLS` (`ID`),
  CONSTRAINT `FK_qscr98vmo3xgcixylek1gmfj0` FOREIGN KEY (`PONT_OF_CONT_ID`) REFERENCES `POINT_OF_CONTACT` (`ID`),
  CONSTRAINT `FK_tgeiib9hk6vw5ys2pt884vxqp` FOREIGN KEY (`ACC_MNGR_ID`) REFERENCES `EMPLOYEE` (`ID`),
  CONSTRAINT `FK_tledabpr2xnlm59veqt5ptbd5` FOREIGN KEY (`DLTD_BY_ID`) REFERENCES `USER` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CLIENT_SEQUENCE`
--

DROP TABLE IF EXISTS `CLIENT_SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CLIENT_SEQUENCE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRTD_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `COUNTRY`
--

DROP TABLE IF EXISTS `COUNTRY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COUNTRY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(10) NOT NULL,
  `DESC` varchar(50) DEFAULT NULL,
  `ISO_3_CODE` varchar(10) DEFAULT NULL,
  `NUM_CODE` int(11) DEFAULT NULL,
  `PH_CODE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=247 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EMPLOLYEE_SEQUENCE`
--

DROP TABLE IF EXISTS `EMPLOLYEE_SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPLOLYEE_SEQUENCE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRTD_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EMPLOYEE`
--

DROP TABLE IF EXISTS `EMPLOYEE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPLOYEE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRTD_DATE` datetime DEFAULT NULL,
  `IS_DLTD` tinyint(1) NOT NULL DEFAULT '0',
  `DLTD_DATE` datetime DEFAULT NULL,
  `UPTD_DATE` datetime DEFAULT NULL,
  `EMP_ID` varchar(15) NOT NULL,
  `JOIN_DATE` date DEFAULT NULL,
  `OFC_EMAIL` varchar(50) DEFAULT NULL,
  `CRTD_BY_ID` bigint(20) DEFAULT NULL,
  `DLTD_BY_ID` bigint(20) DEFAULT NULL,
  `UPTD_BY_ID` bigint(20) DEFAULT NULL,
  `ACC_MNGR_ID` bigint(20) DEFAULT NULL,
  `BASE_LOC_ID` bigint(20) DEFAULT NULL,
  `BUS_UNT_ID` bigint(20) DEFAULT NULL,
  `CNDT_ID` bigint(20) DEFAULT NULL,
  `EMP_USR_ID` bigint(20) DEFAULT NULL,
  `HR_MNGR_ID` bigint(20) DEFAULT NULL,
  `HR_RCTR_ID` bigint(20) DEFAULT NULL,
  `RPTNG_MNGR_ID` bigint(20) DEFAULT NULL,
  `TIME_ZONE_ID` bigint(20) DEFAULT NULL,
  `WRK_LCTN_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_2v3s04062qfuas8c5ops15w1k` (`EMP_ID`),
  KEY `FK_h70posopaxabao9n7adcmmxj5` (`CRTD_BY_ID`),
  KEY `FK_qwp7wn637kxgx6xvrp1u2398i` (`DLTD_BY_ID`),
  KEY `FK_g1lxonpjvjyrd959xdro81n4r` (`UPTD_BY_ID`),
  KEY `FK_8pxyqnu8kw6ggu2hht14ej5qx` (`ACC_MNGR_ID`),
  KEY `FK_dg4ht6a3vawf51m66rswfjbuc` (`BASE_LOC_ID`),
  KEY `FK_d1cksk0dx1swkwyufgryitehh` (`BUS_UNT_ID`),
  KEY `FK_20myukskgkl9iur4omg0kgle9` (`CNDT_ID`),
  KEY `FK_j07gib4liataga0eocein0bqd` (`EMP_USR_ID`),
  KEY `FK_7kmuwe54siubiws1an4regk2j` (`HR_MNGR_ID`),
  KEY `FK_pono3sgujvsyrooy1gd6o0ety` (`HR_RCTR_ID`),
  KEY `FK_3k9wntei8oiaytkvnpu9e7p1x` (`RPTNG_MNGR_ID`),
  KEY `FK_o1e71g1y47w2x51j1rbj9wesg` (`TIME_ZONE_ID`),
  KEY `FK_3ar4je7ipatufvramvnacug33` (`WRK_LCTN_ID`),
  CONSTRAINT `FK_20myukskgkl9iur4omg0kgle9` FOREIGN KEY (`CNDT_ID`) REFERENCES `CANDIDATE` (`ID`),
  CONSTRAINT `FK_3ar4je7ipatufvramvnacug33` FOREIGN KEY (`WRK_LCTN_ID`) REFERENCES `CITY` (`ID`),
  CONSTRAINT `FK_3k9wntei8oiaytkvnpu9e7p1x` FOREIGN KEY (`RPTNG_MNGR_ID`) REFERENCES `EMPLOYEE` (`ID`),
  CONSTRAINT `FK_7kmuwe54siubiws1an4regk2j` FOREIGN KEY (`HR_MNGR_ID`) REFERENCES `EMPLOYEE` (`ID`),
  CONSTRAINT `FK_8pxyqnu8kw6ggu2hht14ej5qx` FOREIGN KEY (`ACC_MNGR_ID`) REFERENCES `EMPLOYEE` (`ID`),
  CONSTRAINT `FK_d1cksk0dx1swkwyufgryitehh` FOREIGN KEY (`BUS_UNT_ID`) REFERENCES `LOOKUP_DTLS` (`ID`),
  CONSTRAINT `FK_dg4ht6a3vawf51m66rswfjbuc` FOREIGN KEY (`BASE_LOC_ID`) REFERENCES `CITY` (`ID`),
  CONSTRAINT `FK_g1lxonpjvjyrd959xdro81n4r` FOREIGN KEY (`UPTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_h70posopaxabao9n7adcmmxj5` FOREIGN KEY (`CRTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_j07gib4liataga0eocein0bqd` FOREIGN KEY (`EMP_USR_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_o1e71g1y47w2x51j1rbj9wesg` FOREIGN KEY (`TIME_ZONE_ID`) REFERENCES `TIME_ZONE` (`ID`),
  CONSTRAINT `FK_pono3sgujvsyrooy1gd6o0ety` FOREIGN KEY (`HR_RCTR_ID`) REFERENCES `EMPLOYEE` (`ID`),
  CONSTRAINT `FK_qwp7wn637kxgx6xvrp1u2398i` FOREIGN KEY (`DLTD_BY_ID`) REFERENCES `USER` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EMPLOYEE_SALARY`
--

DROP TABLE IF EXISTS `EMPLOYEE_SALARY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPLOYEE_SALARY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRTD_DATE` datetime DEFAULT NULL,
  `IS_DLTD` tinyint(1) NOT NULL DEFAULT '0',
  `DLTD_DATE` datetime DEFAULT NULL,
  `UPTD_DATE` datetime DEFAULT NULL,
  `BAS_SAL` double DEFAULT NULL,
  `CON_ALWNC` bigint(20) DEFAULT NULL,
  `ESI_BY_EMP` bigint(20) DEFAULT NULL,
  `ESI_BY_EMPR` bigint(20) DEFAULT NULL,
  `FLEX_BEN_KIT` bigint(20) DEFAULT NULL,
  `GRATUITY` bigint(20) DEFAULT NULL,
  `GRS_CTC` bigint(20) DEFAULT NULL,
  `GRS_SAL` double DEFAULT NULL,
  `HRA` double DEFAULT NULL,
  `LEAV_ENCASH` bigint(20) DEFAULT NULL,
  `MED_ALWNC` bigint(20) DEFAULT NULL,
  `MIN_BAS_SAL` double DEFAULT NULL,
  `MIN_FIXD_SAL` double DEFAULT NULL,
  `NET_TKHM_BFR_TDS` bigint(20) DEFAULT NULL,
  `OFR_LTR_FL_NAME` varchar(15) DEFAULT NULL,
  `PF_COMP_CNTRB` bigint(20) DEFAULT NULL,
  `PF_EMP_CNTRB` bigint(20) DEFAULT NULL,
  `PRF_TAX` bigint(20) DEFAULT NULL,
  `STATUS` varchar(15) DEFAULT NULL,
  `TOT_DEDUC` bigint(20) DEFAULT NULL,
  `CRTD_BY_ID` bigint(20) DEFAULT NULL,
  `DLTD_BY_ID` bigint(20) DEFAULT NULL,
  `UPTD_BY_ID` bigint(20) DEFAULT NULL,
  `CNDT_ID` bigint(20) DEFAULT NULL,
  `CUR_TSK_ID` bigint(20) DEFAULT NULL,
  `GRADE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_198cqif0khicqfcj863gj7k93` (`CRTD_BY_ID`),
  KEY `FK_gylteskg19vmfnecqhwb4ugsa` (`DLTD_BY_ID`),
  KEY `FK_9l0iiof9e9f5h61sgppf4n4ww` (`UPTD_BY_ID`),
  KEY `FK_hd7t0hnogudxvw6utxcanvrcb` (`CNDT_ID`),
  KEY `FK_ggumafbv4ov19a4ymiu9xaqdj` (`CUR_TSK_ID`),
  KEY `FK_t169s9y3nkqq9g82t7knyyrjq` (`GRADE_ID`),
  CONSTRAINT `FK_198cqif0khicqfcj863gj7k93` FOREIGN KEY (`CRTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_9l0iiof9e9f5h61sgppf4n4ww` FOREIGN KEY (`UPTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_ggumafbv4ov19a4ymiu9xaqdj` FOREIGN KEY (`CUR_TSK_ID`) REFERENCES `TASK_LIST` (`ID`),
  CONSTRAINT `FK_gylteskg19vmfnecqhwb4ugsa` FOREIGN KEY (`DLTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_hd7t0hnogudxvw6utxcanvrcb` FOREIGN KEY (`CNDT_ID`) REFERENCES `CANDIDATE` (`ID`),
  CONSTRAINT `FK_t169s9y3nkqq9g82t7knyyrjq` FOREIGN KEY (`GRADE_ID`) REFERENCES `SALARY_GRADE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EVENT`
--

DROP TABLE IF EXISTS `EVENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EVENT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRTD_DATE` datetime DEFAULT NULL,
  `IS_DLTD` tinyint(1) NOT NULL DEFAULT '0',
  `DLTD_DATE` datetime DEFAULT NULL,
  `UPTD_DATE` datetime DEFAULT NULL,
  `IS_ALL_DAY` tinyint(1) NOT NULL DEFAULT '0',
  `BG_COLOR` varchar(10) DEFAULT NULL,
  `BRDR_COLOR` varchar(10) DEFAULT NULL,
  `CLS_NAME` varchar(50) DEFAULT NULL,
  `COLOR` varchar(10) DEFAULT NULL,
  `CONSTRAINT` bigint(20) DEFAULT NULL,
  `DESC` varchar(100) DEFAULT NULL,
  `DUR_EDITABLE` tinyint(1) NOT NULL DEFAULT '1',
  `EDITABLE` tinyint(1) NOT NULL DEFAULT '1',
  `END` datetime DEFAULT NULL,
  `LOCATION` varchar(50) DEFAULT NULL,
  `OVRLAP` tinyint(1) NOT NULL DEFAULT '1',
  `SOURCE` varchar(50) DEFAULT NULL,
  `START` datetime DEFAULT NULL,
  `STRT_EDITABLE` tinyint(1) NOT NULL DEFAULT '1',
  `TXT_COLOR` varchar(10) DEFAULT NULL,
  `TITLE` varchar(50) DEFAULT NULL,
  `URL` varchar(50) DEFAULT NULL,
  `CRTD_BY_ID` bigint(20) DEFAULT NULL,
  `DLTD_BY_ID` bigint(20) DEFAULT NULL,
  `UPTD_BY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_akii6ylfbl24n70w3lsh4ylbh` (`CRTD_BY_ID`),
  KEY `FK_e6b4efoy2noi81y8230brop7` (`DLTD_BY_ID`),
  KEY `FK_548m3ua3idh2rak91t84d80ic` (`UPTD_BY_ID`),
  CONSTRAINT `FK_548m3ua3idh2rak91t84d80ic` FOREIGN KEY (`UPTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_akii6ylfbl24n70w3lsh4ylbh` FOREIGN KEY (`CRTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_e6b4efoy2noi81y8230brop7` FOREIGN KEY (`DLTD_BY_ID`) REFERENCES `USER` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EVENT_USER`
--

DROP TABLE IF EXISTS `EVENT_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EVENT_USER` (
  `EVENT_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  KEY `FK_ibbriw819qkechbkpf80hlwcx` (`USER_ID`),
  KEY `FK_4e6flhs4vnikefnikav2d9p86` (`EVENT_ID`),
  CONSTRAINT `FK_4e6flhs4vnikefnikav2d9p86` FOREIGN KEY (`EVENT_ID`) REFERENCES `EVENT` (`ID`),
  CONSTRAINT `FK_ibbriw819qkechbkpf80hlwcx` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `LOOKUP`
--

DROP TABLE IF EXISTS `LOOKUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOOKUP` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESC` varchar(50) DEFAULT NULL,
  `LOOKUP_KEY` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_lv336v8ueexqgqxctwb4ieks5` (`LOOKUP_KEY`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `LOOKUP_DTLS`
--

DROP TABLE IF EXISTS `LOOKUP_DTLS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOOKUP_DTLS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(10) NOT NULL,
  `DESC` varchar(50) NOT NULL,
  `LOOKUP_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_mqpucbrh69uu8w370hlog5cbq` (`LOOKUP_ID`),
  CONSTRAINT `FK_mqpucbrh69uu8w370hlog5cbq` FOREIGN KEY (`LOOKUP_ID`) REFERENCES `LOOKUP` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `MST_ROLE`
--

DROP TABLE IF EXISTS `MST_ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MST_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `MST_TASK`
--

DROP TABLE IF EXISTS `MST_TASK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MST_TASK` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NXT_TSK_ID` bigint(20) DEFAULT NULL,
  `TSK_DESC` varchar(50) DEFAULT NULL,
  `TSK_KEY` varchar(15) DEFAULT NULL,
  `TSK_OWNR_ROLE` varchar(10) DEFAULT NULL,
  `TSK_ROUTE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OFFICIAL_CARDS`
--

DROP TABLE IF EXISTS `OFFICIAL_CARDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OFFICIAL_CARDS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ESI_NO` varchar(25) DEFAULT NULL,
  `PAN_NO` varchar(25) DEFAULT NULL,
  `PF_NO` varchar(25) DEFAULT NULL,
  `DRV_LIC_NO` varchar(25) DEFAULT NULL,
  `FORX_CRD_AGNCY` varchar(25) DEFAULT NULL,
  `FORX_CDR_NO` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PASSPORT`
--

DROP TABLE IF EXISTS `PASSPORT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PASSPORT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EXP_DATE` date DEFAULT NULL,
  `ISS_DATE` date DEFAULT NULL,
  `PSPRT_NO` varchar(25) DEFAULT NULL,
  `CNTRY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_psyhhw47079unwssjth796iio` (`CNTRY_ID`),
  CONSTRAINT `FK_psyhhw47079unwssjth796iio` FOREIGN KEY (`CNTRY_ID`) REFERENCES `COUNTRY` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PHONE`
--

DROP TABLE IF EXISTS `PHONE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PHONE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PH_NO` varchar(25) DEFAULT NULL,
  `CNTRY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_o98w4hw7q3oqn003kxsirtesb` (`CNTRY_ID`),
  CONSTRAINT `FK_o98w4hw7q3oqn003kxsirtesb` FOREIGN KEY (`CNTRY_ID`) REFERENCES `COUNTRY` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `POINT_OF_CONTACT`
--

DROP TABLE IF EXISTS `POINT_OF_CONTACT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POINT_OF_CONTACT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AREA_OF_WRK` varchar(25) DEFAULT NULL,
  `POC_NM` varchar(255) NOT NULL,
  `CONT_NO_ID` bigint(20) DEFAULT NULL,
  `MOB_NO_ID` bigint(20) DEFAULT NULL,
  `POC_CNTRY_ID` bigint(20) DEFAULT NULL,
  `POC_DSGTN_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_o48k9s0mq9ervdsum6p42c1n4` (`CONT_NO_ID`),
  KEY `FK_5o30dv7r3ilybxy523vygplpx` (`MOB_NO_ID`),
  KEY `FK_k4sxc0i40jy4ic2c9cqi7fb9w` (`POC_CNTRY_ID`),
  KEY `FK_idtc35diwkftb3vr199yjfbfj` (`POC_DSGTN_ID`),
  CONSTRAINT `FK_5o30dv7r3ilybxy523vygplpx` FOREIGN KEY (`MOB_NO_ID`) REFERENCES `PHONE` (`ID`),
  CONSTRAINT `FK_idtc35diwkftb3vr199yjfbfj` FOREIGN KEY (`POC_DSGTN_ID`) REFERENCES `LOOKUP_DTLS` (`ID`),
  CONSTRAINT `FK_k4sxc0i40jy4ic2c9cqi7fb9w` FOREIGN KEY (`POC_CNTRY_ID`) REFERENCES `COUNTRY` (`ID`),
  CONSTRAINT `FK_o48k9s0mq9ervdsum6p42c1n4` FOREIGN KEY (`CONT_NO_ID`) REFERENCES `PHONE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ROLE_TAB`
--

DROP TABLE IF EXISTS `ROLE_TAB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLE_TAB` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TAB_CODE` varchar(25) DEFAULT NULL,
  `TAB_DESC` varchar(50) DEFAULT NULL,
  `MST_ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_2bdmqks0krj2j7ap22dh7ne4j` (`MST_ROLE_ID`),
  CONSTRAINT `FK_2bdmqks0krj2j7ap22dh7ne4j` FOREIGN KEY (`MST_ROLE_ID`) REFERENCES `MST_ROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SALARY_GRADE`
--

DROP TABLE IF EXISTS `SALARY_GRADE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SALARY_GRADE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FIX_SAL` double DEFAULT NULL,
  `GRADE` varchar(10) DEFAULT NULL,
  `MAX_GS_PER_MNT` double DEFAULT NULL,
  `MIN_GS_PER_MNT` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SKILL`
--

DROP TABLE IF EXISTS `SKILL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SKILL` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRIR_EXP_MNT` int(11) DEFAULT NULL,
  `PRIR_EXP_YR` int(11) DEFAULT NULL,
  `SKIL_NAME` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `STATE`
--

DROP TABLE IF EXISTS `STATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STATE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(10) DEFAULT NULL,
  `DESC` varchar(50) DEFAULT NULL,
  `CNTRY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_aoermjnr9o4cfqc0o9vf3q21p` (`CNTRY_ID`),
  CONSTRAINT `FK_aoermjnr9o4cfqc0o9vf3q21p` FOREIGN KEY (`CNTRY_ID`) REFERENCES `COUNTRY` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4120 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TASK_LIST`
--

DROP TABLE IF EXISTS `TASK_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TASK_LIST` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRTD_DATE` datetime DEFAULT NULL,
  `IS_DLTD` tinyint(1) NOT NULL DEFAULT '0',
  `DLTD_DATE` datetime DEFAULT NULL,
  `UPTD_DATE` datetime DEFAULT NULL,
  `COMMENT` longtext,
  `IS_CMPLTD` tinyint(1) NOT NULL DEFAULT '0',
  `STATUS` varchar(15) DEFAULT NULL,
  `TSK_MDL_ID` varchar(15) DEFAULT NULL,
  `CRTD_BY_ID` bigint(20) DEFAULT NULL,
  `DLTD_BY_ID` bigint(20) DEFAULT NULL,
  `UPTD_BY_ID` bigint(20) DEFAULT NULL,
  `TSK_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_dq704ppihmbhy3siul7oxg1tp` (`CRTD_BY_ID`),
  KEY `FK_9kwc3l39gq6q4vqkkql932940` (`DLTD_BY_ID`),
  KEY `FK_a12naqykdmhjwor0d97y73vqc` (`UPTD_BY_ID`),
  KEY `FK_pjpkhefvofmiitjri08jo02nf` (`TSK_ID`),
  CONSTRAINT `FK_9kwc3l39gq6q4vqkkql932940` FOREIGN KEY (`DLTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_a12naqykdmhjwor0d97y73vqc` FOREIGN KEY (`UPTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_dq704ppihmbhy3siul7oxg1tp` FOREIGN KEY (`CRTD_BY_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_pjpkhefvofmiitjri08jo02nf` FOREIGN KEY (`TSK_ID`) REFERENCES `MST_TASK` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TIME_ZONE`
--

DROP TABLE IF EXISTS `TIME_ZONE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TIME_ZONE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UTC_OFFSET` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(50) NOT NULL,
  `FRGT_PSWD_STS` tinyint(1) NOT NULL DEFAULT '0',
  `FRGT_PSWD_TKN` varchar(200) DEFAULT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `SALT` tinyblob,
  `USERNAME` varchar(50) NOT NULL,
  `DSGNTN_ID` bigint(20) NOT NULL,
  `MST_ROLE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_ejfk3g58oxsgbb4ju3u4fhivk` (`EMAIL`),
  UNIQUE KEY `UK_lb5yrvw2c22im784wwrpwuq06` (`USERNAME`),
  KEY `FK_57l6kv2b44nosb2a3ite8a6l5` (`DSGNTN_ID`),
  KEY `FK_2jg9t44encrv9ayj4uw0eur1c` (`MST_ROLE`),
  CONSTRAINT `FK_2jg9t44encrv9ayj4uw0eur1c` FOREIGN KEY (`MST_ROLE`) REFERENCES `MST_ROLE` (`ID`),
  CONSTRAINT `FK_57l6kv2b44nosb2a3ite8a6l5` FOREIGN KEY (`DSGNTN_ID`) REFERENCES `LOOKUP_DTLS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USER_ROLE`
--

DROP TABLE IF EXISTS `USER_ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_ROLE` (
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`USER_ID`),
  KEY `FK_j2j8kpywaghe8i36swcxv8784` (`USER_ID`),
  CONSTRAINT `FK_j2j8kpywaghe8i36swcxv8784` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`),
  CONSTRAINT `FK_oqmdk7xj0ainhxpvi79fkaq3y` FOREIGN KEY (`ROLE_ID`) REFERENCES `MST_ROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-31  9:17:47
