/*
 Navicat Premium Data Transfer

 Source Server         : web
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : GS1-128

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 17/05/2020 17:35:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for characterset
-- ----------------------------
DROP TABLE IF EXISTS `characterset`;
CREATE TABLE `characterset` (
  `A` varchar(255) DEFAULT NULL,
  `B` varchar(255) DEFAULT NULL,
  `C` varchar(255) DEFAULT NULL,
  `num` int(10) DEFAULT NULL,
  `no` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of characterset
-- ----------------------------
BEGIN;
INSERT INTO `characterset` VALUES ('space', 'space', '00', 212222, 0);
INSERT INTO `characterset` VALUES ('!', '!', '01', 222122, 1);
INSERT INTO `characterset` VALUES ('\"', '\"', '02', 222221, 2);
INSERT INTO `characterset` VALUES ('#', '#', '03', 121223, 3);
INSERT INTO `characterset` VALUES ('$', '$', '04', 121322, 4);
INSERT INTO `characterset` VALUES ('%', '%', '05', 131222, 5);
INSERT INTO `characterset` VALUES ('&', '&', '06', 122213, 6);
INSERT INTO `characterset` VALUES ('…', '…', '07', 122312, 7);
INSERT INTO `characterset` VALUES ('(', '(', '08', 132212, 8);
INSERT INTO `characterset` VALUES (')', ')', '09', 221213, 9);
INSERT INTO `characterset` VALUES ('*', '*', '10', 221312, 10);
INSERT INTO `characterset` VALUES ('+', '+', '11', 231212, 11);
INSERT INTO `characterset` VALUES (',', ',', '12', 112232, 12);
INSERT INTO `characterset` VALUES ('-', '-', '13', 122132, 13);
INSERT INTO `characterset` VALUES ('。', '。', '14', 122231, 14);
INSERT INTO `characterset` VALUES ('/', '/', '15', 113222, 15);
INSERT INTO `characterset` VALUES ('0', '0', '16', 123122, 16);
INSERT INTO `characterset` VALUES ('1', '1', '17', 123221, 17);
INSERT INTO `characterset` VALUES ('2', '2', '18', 223211, 18);
INSERT INTO `characterset` VALUES ('3', '3', '19', 221132, 19);
INSERT INTO `characterset` VALUES ('4', '4', '20', 221231, 20);
INSERT INTO `characterset` VALUES ('5', '5', '21', 213212, 21);
INSERT INTO `characterset` VALUES ('6', '6', '22', 223112, 22);
INSERT INTO `characterset` VALUES ('7', '7', '23', 312131, 23);
INSERT INTO `characterset` VALUES ('8', '8', '24', 311222, 24);
INSERT INTO `characterset` VALUES ('9', '9', '25', 321122, 25);
INSERT INTO `characterset` VALUES (':', ':', '26', 321221, 26);
INSERT INTO `characterset` VALUES (';', ';', '27', 312212, 27);
INSERT INTO `characterset` VALUES ('<', '<', '28', 322112, 28);
INSERT INTO `characterset` VALUES ('=', '=', '29', 322211, 29);
INSERT INTO `characterset` VALUES ('>', '>', '30', 212123, 30);
INSERT INTO `characterset` VALUES ('?', '?', '31', 212321, 31);
INSERT INTO `characterset` VALUES ('@', '@', '32', 232121, 32);
INSERT INTO `characterset` VALUES ('A', 'A', '33', 111323, 33);
INSERT INTO `characterset` VALUES ('B', 'B', '34', 131123, 34);
INSERT INTO `characterset` VALUES ('C', 'C', '35', 131321, 35);
INSERT INTO `characterset` VALUES ('D', 'D', '36', 112313, 36);
INSERT INTO `characterset` VALUES ('E', 'E', '37', 132113, 37);
INSERT INTO `characterset` VALUES ('F', 'F', '38', 132311, 38);
INSERT INTO `characterset` VALUES ('G', 'G', '39', 211313, 39);
INSERT INTO `characterset` VALUES ('H', 'H', '40', 231113, 40);
INSERT INTO `characterset` VALUES ('I', 'I', '41', 231311, 41);
INSERT INTO `characterset` VALUES ('G', 'G', '42', 112133, 42);
INSERT INTO `characterset` VALUES ('K', 'K', '43', 112331, 43);
INSERT INTO `characterset` VALUES ('L', 'L', '44', 132131, 44);
INSERT INTO `characterset` VALUES ('M', 'M', '45', 113123, 45);
INSERT INTO `characterset` VALUES ('N', 'N', '46', 113321, 46);
INSERT INTO `characterset` VALUES ('O', 'O', '47', 133121, 47);
INSERT INTO `characterset` VALUES ('P', 'P', '48', 313121, 48);
INSERT INTO `characterset` VALUES ('Q', 'Q', '49', 211331, 49);
INSERT INTO `characterset` VALUES ('R', 'R', '50', 231131, 50);
INSERT INTO `characterset` VALUES ('S', 'S', '51', 213113, 51);
INSERT INTO `characterset` VALUES ('T', 'T', '52', 213311, 52);
INSERT INTO `characterset` VALUES ('U', 'U', '53', 213131, 53);
INSERT INTO `characterset` VALUES ('V', 'V', '54', 311123, 54);
INSERT INTO `characterset` VALUES ('W', 'W', '55', 311321, 55);
INSERT INTO `characterset` VALUES ('X', 'X', '56', 331121, 56);
INSERT INTO `characterset` VALUES ('Y', 'Y', '57', 312113, 57);
INSERT INTO `characterset` VALUES ('Z', 'Z', '58', 312311, 58);
INSERT INTO `characterset` VALUES ('[', '[', '59', 332111, 59);
INSERT INTO `characterset` VALUES ('\\', '\\', '60', 314111, 60);
INSERT INTO `characterset` VALUES (']', ']', '61', 221411, 61);
INSERT INTO `characterset` VALUES ('^', '^', '62', 431111, 62);
INSERT INTO `characterset` VALUES ('_', '_', '63', 111224, 63);
INSERT INTO `characterset` VALUES ('NUL', '\'', '64', 111422, 64);
INSERT INTO `characterset` VALUES ('SOH', 'a', '65', 121124, 65);
INSERT INTO `characterset` VALUES ('STX', 'b', '66', 121421, 66);
INSERT INTO `characterset` VALUES ('ETX', 'c', '67', 141122, 67);
INSERT INTO `characterset` VALUES ('EOT', 'd', '68', 141221, 68);
INSERT INTO `characterset` VALUES ('ENQ', 'e', '69', 112214, 69);
INSERT INTO `characterset` VALUES ('ACK', 'f', '70', 112412, 70);
INSERT INTO `characterset` VALUES ('BEL', 'g', '71', 122114, 71);
INSERT INTO `characterset` VALUES ('BS', 'h', '72', 122411, 72);
INSERT INTO `characterset` VALUES ('HT', 'i', '73', 142112, 73);
INSERT INTO `characterset` VALUES ('LF', 'j', '74', 142211, 74);
INSERT INTO `characterset` VALUES ('VT', 'k', '75', 241211, 75);
INSERT INTO `characterset` VALUES ('FF', 'l', '76', 221114, 76);
INSERT INTO `characterset` VALUES ('CR', 'm', '77', 413111, 77);
INSERT INTO `characterset` VALUES ('SO', 'n', '78', 241112, 78);
INSERT INTO `characterset` VALUES ('SI', 'o', '79', 134111, 79);
INSERT INTO `characterset` VALUES ('DLE', 'p', '80', 111242, 80);
INSERT INTO `characterset` VALUES ('DC1', 'q', '81', 121142, 81);
INSERT INTO `characterset` VALUES ('DC2', 'r', '82', 121241, 82);
INSERT INTO `characterset` VALUES ('DC3', 's', '83', 114212, 83);
INSERT INTO `characterset` VALUES ('DC4', 't', '84', 124112, 84);
INSERT INTO `characterset` VALUES ('NAK', 'u', '85', 124211, 85);
INSERT INTO `characterset` VALUES ('SYN', 'v', '86', 411212, 86);
INSERT INTO `characterset` VALUES ('ETB', 'w', '87', 421112, 87);
INSERT INTO `characterset` VALUES ('CAN', 'x', '88', 421211, 88);
INSERT INTO `characterset` VALUES ('EM', 'y', '89', 212141, 89);
INSERT INTO `characterset` VALUES ('SUB', 'z', '90', 214121, 90);
INSERT INTO `characterset` VALUES ('ESC', '{', '91', 412121, 91);
INSERT INTO `characterset` VALUES ('FS', '|', '92', 111143, NULL);
INSERT INTO `characterset` VALUES ('GS', '}', '93', 111341, 93);
INSERT INTO `characterset` VALUES ('RS', '~', '94', 131141, 94);
INSERT INTO `characterset` VALUES ('US', 'DEL', '95', 114113, 95);
INSERT INTO `characterset` VALUES ('FNC3', 'FNC3', '96', 114311, 96);
INSERT INTO `characterset` VALUES ('FNC2', 'FNC2', '97', 411113, 97);
INSERT INTO `characterset` VALUES ('SHIFT', 'SHIFT', '98', 411311, 98);
INSERT INTO `characterset` VALUES ('CODE C', 'CODE C', '99', 113141, 99);
INSERT INTO `characterset` VALUES ('CODE B', 'FNC4', 'CODEB', 114131, 100);
INSERT INTO `characterset` VALUES ('FNC4', 'CODE A', 'CODEA', 311141, 101);
INSERT INTO `characterset` VALUES ('FNC1', 'FNC1', 'FNC1', 411131, 102);
INSERT INTO `characterset` VALUES ('StartA', 'StartA', 'StartA', 211412, 103);
INSERT INTO `characterset` VALUES ('StartB', 'StartB', 'StartB', 211214, 104);
INSERT INTO `characterset` VALUES ('StartC', 'StartC', 'StartC', 211232, 105);
INSERT INTO `characterset` VALUES (NULL, NULL, NULL, NULL, NULL);
INSERT INTO `characterset` VALUES (NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
