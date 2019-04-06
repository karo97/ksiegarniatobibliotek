-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 07 Mar 2019, 19:43
-- Wersja serwera: 10.1.36-MariaDB
-- Wersja PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `ksiegarniainternetowa`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ksiazka`
--

CREATE TABLE `ksiazka` (
  `id` int(11) NOT NULL,
  `tytul` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `autor` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `opis` text COLLATE utf8_unicode_ci NOT NULL,
  `cena` decimal(5,2) NOT NULL,
  `zarchiwizowana` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `ksiazka`
--

INSERT INTO `ksiazka` (`id`, `tytul`, `autor`, `opis`, `cena`, `zarchiwizowana`) VALUES
(1, 'Tytuł #1', 'Autor #1', 'Opis...', '19.99', 0),
(2, 'Tytuł #2', 'Autor #1', 'Opis...', '20.00', 0),
(3, 'Tytuł #3', 'Autor #2', 'Opis...', '17.90', 0),
(4, 'Tytuł #4', 'Autor #3', 'Opis...', '11.00', 0),
(5, 'Tytuł #5', 'Autor #4', 'Opis...', '10.99', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE `uzytkownik` (
  `id` int(11) NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `login` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `haslo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `imie` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nazwisko` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `rola` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`id`, `email`, `login`, `haslo`, `imie`, `nazwisko`, `rola`) VALUES
(1, 'user@user.user', 'user', 'password', 'Piotr', 'Kowalski', 'user'),
(2, 'admin@admin.admin', 'admin', 'password', 'Paweł', 'Kowalski', 'admin');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienie`
--

CREATE TABLE `zamowienie` (
  `id` int(11) NOT NULL,
  `id_uzytkownika` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `cena` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `zamowienie`
--

INSERT INTO `zamowienie` (`id`, `id_uzytkownika`, `data`, `cena`) VALUES
(1, 1, '2019-03-07 19:40:55', '30.99'),
(2, 2, '2019-03-07 19:41:35', '39.99');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienie_ksiazka`
--

CREATE TABLE `zamowienie_ksiazka` (
  `id` int(11) NOT NULL,
  `zamowienie_id` int(11) NOT NULL,
  `ksiazka_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `zamowienie_ksiazka`
--

INSERT INTO `zamowienie_ksiazka` (`id`, `zamowienie_id`, `ksiazka_id`) VALUES
(1, 1, 2),
(2, 1, 5),
(3, 2, 2),
(4, 2, 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `ksiazka`
--
ALTER TABLE `ksiazka`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_uzytkownika` (`id_uzytkownika`);

--
-- Indeksy dla tabeli `zamowienie_ksiazka`
--
ALTER TABLE `zamowienie_ksiazka`
  ADD PRIMARY KEY (`id`),
  ADD KEY `zamowienie_id` (`zamowienie_id`),
  ADD KEY `ksiazka_id` (`ksiazka_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `ksiazka`
--
ALTER TABLE `ksiazka`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `zamowienie_ksiazka`
--
ALTER TABLE `zamowienie_ksiazka`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD CONSTRAINT `zamowienie_ibfk_1` FOREIGN KEY (`id_uzytkownika`) REFERENCES `uzytkownik` (`id`);

--
-- Ograniczenia dla tabeli `zamowienie_ksiazka`
--
ALTER TABLE `zamowienie_ksiazka`
  ADD CONSTRAINT `zamowienie_ksiazka_ibfk_1` FOREIGN KEY (`zamowienie_id`) REFERENCES `zamowienie` (`id`),
  ADD CONSTRAINT `zamowienie_ksiazka_ibfk_2` FOREIGN KEY (`ksiazka_id`) REFERENCES `ksiazka` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
