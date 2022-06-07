/*
 Navicat MySQL Data Transfer

 Source Server         : rds
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 114.116.221.10:3306
 Source Schema         : speed_survey

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 07/06/2022 23:52:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for assignment
-- ----------------------------
DROP TABLE IF EXISTS `assignment`;
CREATE TABLE `assignment`  (
  `id` int NOT NULL,
  `user_id` int NOT NULL COMMENT '用户id',
  `questionnaire_id` int NOT NULL COMMENT '组织内问卷id',
  `is_finished` int NOT NULL COMMENT '0未完成，1完成',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `questionnaire_id`(`questionnaire_id`) USING BTREE,
  CONSTRAINT `assignment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assignment_ibfk_2` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of assignment
-- ----------------------------

-- ----------------------------
-- Table structure for ip_restrict
-- ----------------------------
DROP TABLE IF EXISTS `ip_restrict`;
CREATE TABLE `ip_restrict`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `questionnaire_id` int NOT NULL COMMENT '所填问卷id',
  `ip` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '填写者ip',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_ip_restrict_questionnaire_1`(`questionnaire_id`) USING BTREE,
  CONSTRAINT `fk_ip_restrict_questionnaire_1` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ip_restrict
-- ----------------------------

-- ----------------------------
-- Table structure for option
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '选项id',
  `question_id` int NOT NULL COMMENT '问题id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项内容',
  `order_number` int NOT NULL COMMENT '选项序号',
  `count` int NOT NULL DEFAULT 0 COMMENT '该选项被选择的次数。例如有5人选A，则A选项count==5',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_option_question_1`(`question_id`) USING BTREE,
  CONSTRAINT `fk_option_question_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of option
-- ----------------------------
INSERT INTO `option` VALUES (2, 3, '女', 3, 0);
INSERT INTO `option` VALUES (3, 4, '1911101', 1, 0);
INSERT INTO `option` VALUES (4, 4, '1911102', 2, 0);
INSERT INTO `option` VALUES (5, 4, '1911103', 3, 0);
INSERT INTO `option` VALUES (6, 4, '1911104', 4, 0);
INSERT INTO `option` VALUES (7, 4, '1911105', 5, 0);
INSERT INTO `option` VALUES (8, 4, '1911301', 6, 0);
INSERT INTO `option` VALUES (9, 5, '看书', 1, 0);
INSERT INTO `option` VALUES (10, 5, '打游戏', 2, 0);
INSERT INTO `option` VALUES (11, 5, '跑步', 3, 0);
INSERT INTO `option` VALUES (12, 5, '旅游', 4, 0);
INSERT INTO `option` VALUES (13, 5, '摄影', 5, 0);
INSERT INTO `option` VALUES (14, 8, '考研', 1, 0);
INSERT INTO `option` VALUES (15, 8, '就业', 2, 0);
INSERT INTO `option` VALUES (16, 8, '出国', 3, 0);

-- ----------------------------
-- Table structure for option_result
-- ----------------------------
DROP TABLE IF EXISTS `option_result`;
CREATE TABLE `option_result`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '选择题答题id',
  `user_id` int NULL DEFAULT NULL COMMENT '填写者id',
  `question_id` int NOT NULL COMMENT '问题id',
  `option_id` int NOT NULL COMMENT '选项id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_option_result_user_1`(`user_id`) USING BTREE,
  INDEX `fk_option_result_question_1`(`question_id`) USING BTREE,
  INDEX `fk_option_result_option_1`(`option_id`) USING BTREE,
  CONSTRAINT `fk_option_result_option_1` FOREIGN KEY (`option_id`) REFERENCES `option` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_option_result_question_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_option_result_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of option_result
-- ----------------------------

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织描述',
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织标志',
  `invite_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES (1, ' 哈工大威海', NULL, NULL, NULL);
INSERT INTO `organization` VALUES (2, '哈工大深圳', NULL, NULL, NULL);
INSERT INTO `organization` VALUES (3, '哈工大本部', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '问题id',
  `questionnaire_id` int NOT NULL COMMENT '问卷id',
  `order_number` int NOT NULL COMMENT '问题序号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `type` int NOT NULL COMMENT '0填空/1单选/2多选',
  `is_required` int NULL DEFAULT 0 COMMENT '0非必填/1必填',
  `is_private` int NULL DEFAULT 0 COMMENT '0公开/1隐私',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_question_questionnaire_1`(`questionnaire_id`) USING BTREE,
  CONSTRAINT `fk_question_questionnaire_1` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 1, 1, '姓名', 0, 1, 0);
INSERT INTO `question` VALUES (2, 1, 2, '学号', 0, 1, 0);
INSERT INTO `question` VALUES (3, 1, 3, '性别', 1, 1, 0);
INSERT INTO `question` VALUES (4, 1, 4, '班级', 1, 1, 0);
INSERT INTO `question` VALUES (5, 1, 5, '爱好', 2, 1, 0);
INSERT INTO `question` VALUES (6, 2, 1, '姓名', 0, 1, 0);
INSERT INTO `question` VALUES (7, 2, 2, '学号', 0, 1, 0);
INSERT INTO `question` VALUES (8, 2, 3, '意向', 1, 1, 0);
INSERT INTO `question` VALUES (9, 2, 4, '其他问题', 0, 0, 0);

-- ----------------------------
-- Table structure for questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire`;
CREATE TABLE `questionnaire`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '问卷id',
  `user_id` int NOT NULL COMMENT '发布者id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `password` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问密码; 为空时表示无密码的公开问卷',
  `ip_limit` int NULL DEFAULT NULL COMMENT '0不限制/1限制ip',
  `belong_organization` int NULL DEFAULT 0 COMMENT '0普通问卷/组织id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问卷url',
  `state` int NULL DEFAULT NULL COMMENT '0已创建/1进行中/2已结束',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_questionnaire_user_1`(`user_id`) USING BTREE,
  CONSTRAINT `fk_questionnaire_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of questionnaire
-- ----------------------------
INSERT INTO `questionnaire` VALUES (1, 2, '个人信息统计', NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `questionnaire` VALUES (2, 3, '毕业意向统计', NULL, NULL, NULL, 0, NULL, NULL);

-- ----------------------------
-- Table structure for text_result
-- ----------------------------
DROP TABLE IF EXISTS `text_result`;
CREATE TABLE `text_result`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '填空题答题id',
  `user_id` int NULL DEFAULT NULL COMMENT '填写者id',
  `question_id` int NOT NULL COMMENT '问题id',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回答',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_text_result_user_1`(`user_id`) USING BTREE,
  INDEX `fk_text_result_question_1`(`question_id`) USING BTREE,
  CONSTRAINT `fk_text_result_question_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_text_result_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of text_result
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `authority` int NULL DEFAULT 0 COMMENT '0普通用户/1高级用户/2管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '杨蛟', '123456', 'hitcez@163.com', 0);
INSERT INTO `user` VALUES (3, '张三', '123456', 'zhangsan@example.com', 0);
INSERT INTO `user` VALUES (4, '李四', '123456', 'lisi@example.com', 0);
INSERT INTO `user` VALUES (5, '王五', '123456', 'wangwu@example.com', 0);
INSERT INTO `user` VALUES (6, '赵六', '123456', 'zhaoliu@example.com', 0);
INSERT INTO `user` VALUES (7, '孙七', '123456', 'sunqi@example.com', 0);

-- ----------------------------
-- Table structure for user_organization
-- ----------------------------
DROP TABLE IF EXISTS `user_organization`;
CREATE TABLE `user_organization`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `organization_id` int NOT NULL COMMENT '组织id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_organization_user_1`(`user_id`) USING BTREE,
  INDEX `fk_user_organization_organization_1`(`organization_id`) USING BTREE,
  CONSTRAINT `fk_user_organization_organization_1` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_organization_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_organization
-- ----------------------------
INSERT INTO `user_organization` VALUES (1, 2, 1);
INSERT INTO `user_organization` VALUES (2, 3, 2);
INSERT INTO `user_organization` VALUES (3, 4, 2);
INSERT INTO `user_organization` VALUES (4, 5, 2);
INSERT INTO `user_organization` VALUES (5, 6, 3);

SET FOREIGN_KEY_CHECKS = 1;
