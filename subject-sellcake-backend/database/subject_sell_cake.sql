-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 06, 2019 lúc 08:23 PM
-- Phiên bản máy phục vụ: 10.1.37-MariaDB
-- Phiên bản PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `subject_sell_cake`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bills`
--

CREATE TABLE `bills` (
  `bill_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `customer_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_order` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `payment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `total` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `customer_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `customer_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `news`
--

CREATE TABLE `news` (
  `new_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `images` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_types`
--

CREATE TABLE `product_types` (
  `product_type_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `product_type_description` text COLLATE utf8_unicode_ci,
  `product_type_image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_type_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `session`
--

CREATE TABLE `session` (
  `token_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `account_login_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `session_data` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `session`
--

INSERT INTO `session` (`token_id`, `account_login_id`, `login_date`, `session_data`) VALUES
('9e0466ea-c57f-422d-b233-132cf15d5b62', '', '2019-03-21 15:05:57', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('ddaad38c-bf26-4c79-bf0c-2ee9f8e74ada', '', '2019-03-21 15:06:14', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('c954b4ca-883e-430a-b35b-df772e8f3cc3', '', '2019-03-23 05:57:07', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('028486b8-f853-4b4d-821f-696e7057e416', '', '2019-03-23 05:58:41', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('23355673-2225-4fe9-9652-ce076e805370', '', '2019-03-26 08:09:19', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('e00f8d1e-b984-4e89-90cd-5ac8b17e179a', '', '2019-03-26 08:32:54', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('d33ccdb7-7474-4c1f-84a4-67c90cb09cae', '', '2019-03-26 08:37:36', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('3870b2e3-28a9-4df0-8b88-0edb0769524b', '', '2019-03-26 09:51:03', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('5f2164ca-963a-4a79-9c08-058e240bf4b5', '', '2019-03-27 05:49:23', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('c0d958a8-2cf0-407e-8b95-5f04267e0398', '', '2019-03-27 05:53:18', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('69f49e4a-4f86-4b27-96ed-3adea893978e', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-27 05:53:48', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('5cce89b1-92b6-4d63-bee3-1a4ec6b3f7da', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-27 06:25:47', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('f818e9e6-ca32-423e-b2b0-9e8779541d9a', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-27 06:26:04', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('7988a82f-81ff-426b-b8e6-219900dec59c', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-27 16:26:23', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('f6e278e0-4173-40ec-8101-7e9f0a2c82b7', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-27 16:28:24', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('457c3276-4cc3-4977-a078-79e1ce83c770', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-27 16:30:38', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('8d40ad0e-8aae-4ff2-afbc-02a09d30c39a', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-27 16:31:43', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('8da5db55-c709-42f6-9e9b-d44cbf5a742e', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 05:52:26', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('87dfc6fd-b3d8-4389-b5e6-f37c58b08f55', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 08:12:28', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('5fafcea4-ee69-4457-ae72-9d5d1721fd5e', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 08:12:37', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('484d032f-ddb5-4cfa-9170-e37268cd5c05', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 08:12:55', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('97ab8e8c-41a8-47d0-96c0-05112dcd33c8', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 08:13:54', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('f2995e86-0150-4964-a45a-541735a50802', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 08:17:01', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('b05eebd9-66cb-4330-a15f-edac0467eb79', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 08:42:34', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('7711923b-cf39-46b3-b251-8c5e5510010c', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 10:36:07', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('2092d695-5a76-4e70-ba43-b46fbd449c12', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 11:46:34', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('25642492-2661-4d78-b4ef-9c517381221b', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 12:55:49', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('6fa3abc6-2564-4779-8a36-83c998565324', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 15:06:37', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('e4bfce6e-7a5d-4e13-b605-8838d1f026fd', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 17:48:39', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('74c5f8cb-b58b-4db5-b58a-8b13491033f7', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 18:43:49', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('a2570b25-a726-4fae-88dd-f8980886ab3d', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-03-28 18:46:39', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('81d7ef55-2f6b-42d9-a467-142c08ea6e31', '', '2019-03-28 18:47:26', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('2da26378-be55-4d9c-a37e-a5736d1c8131', '', '2019-03-28 18:49:39', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('c5bcdd60-b77c-4796-baf6-85c77633fdb5', '', '2019-03-28 19:19:26', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('2e0411db-ecee-4721-b960-2af261e57d18', '', '2019-03-29 12:44:21', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('77d0a0df-ae24-4eb8-b875-7dca8a886e13', '', '2019-04-02 11:35:31', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('4f475f07-7cb3-4883-b395-38a8ee9f393c', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-02 11:37:15', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('4972f1e7-0428-49dd-af55-687338205e4a', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-03 07:20:51', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('9de042c1-eaa6-4c83-9a2c-ee4d2eab0c36', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-03 16:32:09', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('f64166e8-1198-48d3-a9bf-f6682c564c9c', '', '2019-04-03 16:32:19', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('32e7d47c-c5fd-405a-a1e4-3b0f9c210def', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-04 10:25:12', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('c632c608-7040-4d29-9899-165cdd2aa3e9', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-06 05:05:18', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('64c7f55c-7d7c-464b-837d-c5de6dc9bdba', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-06 05:29:52', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('a390c17d-df89-4d8f-92bf-39647460432f', '', '2019-04-06 06:25:57', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('8b8ec1f9-acd3-4d36-aaae-cb631d1f64af', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-06 14:04:43', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('84ca31e3-d547-450a-9902-9edfa5140022', '', '2019-04-06 14:07:42', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('6aa3b40e-aae3-49c0-87bf-613e63df7e6e', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-06 14:46:37', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('faf11f0b-42f7-40bc-99ce-c35fd9d722aa', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-06 15:01:06', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('cc88208e-9059-4d1a-877f-1d52671370dd', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-06 15:19:19', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('1f9009df-cd44-4203-be6a-9f3e3237933c', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-06 15:31:09', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('484030db-1fdf-48f6-857c-2838f7feedbb', '', '2019-04-06 15:33:08', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}'),
('7f94e99c-16ae-4993-ba8e-25ce8845be28', '6402bf6d-06e2-4464-8fb3-67b63e9f40bf', '2019-04-06 17:31:33', '{\"id\":\"6402bf6d-06e2-4464-8fb3-67b63e9f40bf\",\"username\":\"datit1997\",\"email\":\"phamvandat0029@gmail.com\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Pham\",\"lastName\":\"Dat\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"ADMIN\"}'),
('7edb3f13-395c-4189-8b2c-a7c9f1e85247', '', '2019-04-06 17:51:37', '{\"id\":\"\",\"username\":\"vandat1997\",\"email\":\"15130029@st.hcmuaf.edu.vn\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"firstName\":\"Phạm\",\"lastName\":\"Đạt\",\"lang\":\"vi\",\"enabled\":true,\"role\":\"USER\"}');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `slides`
--

CREATE TABLE `slides` (
  `slide_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `slide_image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `slide_links` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lang` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_id`, `address`, `create_at`, `email`, `first_name`, `full_name`, `lang`, `update_at`, `last_name`, `password`, `phone`, `role`, `salt`, `status`, `user_name`) VALUES
('6402bf6d-06e2-4464-8fb3-67b63e9f40bf', 'Tiền Giang', '2019-03-13 14:45:30', 'phamvandat0029@gmail.com', 'Pham', 'Phạm Văn Đạt', 'vi', '2019-03-13 14:45:30', 'Dat', 'e10adc3949ba59abbe56e057f20f883e', '0396234467', 'ADMIN', 'n1dDwKdQ', 1, 'datit1997');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`bill_id`);

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`);

--
-- Chỉ mục cho bảng `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`new_id`);

--
-- Chỉ mục cho bảng `product_types`
--
ALTER TABLE `product_types`
  ADD PRIMARY KEY (`product_type_id`);

--
-- Chỉ mục cho bảng `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`token_id`);

--
-- Chỉ mục cho bảng `slides`
--
ALTER TABLE `slides`
  ADD PRIMARY KEY (`slide_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
