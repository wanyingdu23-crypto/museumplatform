/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE TABLE IF NOT EXISTS `puzzle_artifacts` (
  `id` bigint NOT NULL,
  `title` varchar(160) COLLATE utf8mb4_unicode_ci NOT NULL,
  `title_zh` varchar(160) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image_label` varchar(160) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image_label_zh` varchar(160) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image_url` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `thumbnail_url` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `description_zh` text COLLATE utf8mb4_unicode_ci,
  `background_story` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `background_story_zh` text COLLATE utf8mb4_unicode_ci,
  `hint_text` text COLLATE utf8mb4_unicode_ci,
  `hint_text_zh` text COLLATE utf8mb4_unicode_ci,
  `category` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `shape_tags` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `culture` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_puzzle_artifacts_title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DELETE FROM `puzzle_artifacts`;

INSERT INTO `puzzle_artifacts` (
  `id`,
  `title`,
  `title_zh`,
  `image_label`,
  `image_label_zh`,
  `image_url`,
  `thumbnail_url`,
  `description`,
  `description_zh`,
  `background_story`,
  `background_story_zh`,
  `hint_text`,
  `hint_text_zh`,
  `category`,
  `shape_tags`,
  `culture`,
  `department`
) VALUES
(
  1001,
  'Jade Booklet',
  '玉册',
  'Jade Booklet',
  '玉册',
  '/assets/puzzle-artifacts/jade-booklet.jpg',
  '/assets/puzzle-artifacts/jade-booklet.jpg',
  'A Ming dynasty jade booklet carved from pale celadon-white jade with dark mottling. It has a flattened trapezoidal form, with imperial inscription text, dragons, phoenixes, and wave motifs carved on both sides.',
  '明代青白玉玉册，玉色间有黑褐色沁色，体扁平，大致呈长梯形。两面刻有册命文字以及龙凤、海水等纹饰，整体华丽工整。',
  'The phrase “May the Yellow River be as narrow as a belt and Mount Tai as small as a whetstone” comes from words used by emperors when rewarding great merit. The term “ce ming” refers to formal imperial commands used for investiture and major ritual announcements.',
  '“使河如带，泰山若厉，国以永宁，爰及苗裔”出自古代帝王封赏功臣时的用语。“册命”则指帝王用于告祭天地宗庙、册立太子皇后及诸王大臣的正式命令。',
  'Look for the trapezoidal jade plaque and the carved dragons framing the inscription.',
  '注意长梯形玉板的外轮廓，以及文字周围雕刻的龙纹。',
  'Jade',
  'jade, carved, trapezoid, inscription, dragon',
  'Ming',
  'Ceremonial Objects'
),
(
  1002,
  'Proto-Porcelain Dou',
  '原始瓷豆',
  'Proto-Porcelain Dou',
  '原始瓷豆',
  '/assets/puzzle-artifacts/proto-porcelain-dou.jpg',
  '/assets/puzzle-artifacts/proto-porcelain-dou.jpg',
  'A Western Zhou proto-porcelain dou with a shallow bowl, thick stem, flaring ring foot, and green glaze. It reflects the improved ceramic technology and expanding vessel forms of the period.',
  '西周原始瓷豆，豆盘敛口，浅腹，粗柄，圈足侈大，通体施青釉，体现了当时原始瓷工艺的提升。',
  'Compared with the Shang period, Western Zhou proto-porcelain developed in both technique and variety. Finds are widely distributed, especially in the south, showing an important early stage in the history of Chinese porcelain.',
  '与商代相比，西周原始瓷在工艺和器型上都有发展，出土范围也更加广泛，尤其南方地区发现数量较多，是中国早期瓷器发展中的重要阶段。',
  'Notice the shallow dish above the thick stem and broad ring foot.',
  '注意浅腹豆盘、粗柄和外撇圈足的组合。',
  'Ceramic',
  'dish, stem, ring-foot, glaze, proto-porcelain',
  'Western Zhou',
  'Ceramics'
),
(
  1003,
  'Celadon Zodiac Figurines of Rat, Ox, and Monkey',
  '青瓷鼠、牛、猴俑',
  'Celadon Zodiac Figurines',
  '青瓷生肖俑',
  '/assets/puzzle-artifacts/celadon-zodiac-figurines.jpg',
  '/assets/puzzle-artifacts/celadon-zodiac-figurines.jpg',
  'Three Sui dynasty celadon zodiac figurines representing the rat, ox, and monkey. Each has a human body, animal head, seated pose, official robe, and yellow-green glaze.',
  '隋代青瓷十二生肖俑中的三件，分别为鼠、牛、猴。它们均为人身兽首、着衣持笏、盘坐姿态，釉色青黄，制作精致。',
  'Although zodiac belief appeared earlier in textual records, zodiac figurines in this sculptural form are generally associated with the Sui period and later. These figures show the combination of belief, funerary practice, and ceramic artistry.',
  '虽然十二生肖观念更早便已见于文献，但以这种俑像形式集中出现，一般认为始于隋代，体现了信仰、丧葬与陶瓷艺术的结合。',
  'Look for the mix of human robes and animal heads in three seated figures.',
  '注意三件俑都呈现人身着衣、兽首盘坐的样式。',
  'Figurine',
  'figurine, zodiac, celadon, seated, animal-head',
  'Sui',
  'Ceramics'
),
(
  1004,
  'Dragon-Pattern Jade Bi',
  '龙纹玉璧',
  'Dragon-Pattern Jade Bi',
  '龙纹玉璧',
  '/assets/puzzle-artifacts/dragon-jade-bi.jpg',
  '/assets/puzzle-artifacts/dragon-jade-bi.jpg',
  'A Western Han jade bi disk, 26.3 cm in diameter, decorated with dense grain patterning and a ring of stylized dragon motifs. Its polished surface shows the high level of Han jade craftsmanship.',
  '西汉龙纹玉璧，直径26.3厘米，璧面布满细密谷纹，外缘饰一圈图案化龙纹，玉质坚硬晶莹，工艺精细。',
  'Bi, cong, zhang, and huang were important ritual jades in earlier periods. By the Han dynasty their ceremonial use had declined, but jade bi were still used in grand sacrifices and burials, often placed on the body or in the coffin.',
  '璧、琮、璋、璜等在先秦属于重要祭玉。到汉代，璧的礼仪功能虽有所减弱，但在大祭祀与墓葬中仍被使用，常置于死者身上或棺椁之中。',
  'Find the round jade disk with a central hole and tightly arranged grain motifs.',
  '注意中央穿孔的圆形玉璧，以及密集整齐的谷纹。',
  'Jade',
  'round, jade, disk, central-hole, dragon',
  'Western Han',
  'Jade and Stone'
),
(
  1005,
  'Dragon-Pattern Square Paper',
  '龙纹斗方纸',
  'Dragon-Pattern Square Paper',
  '龙纹斗方纸',
  '/assets/puzzle-artifacts/dragon-square-paper.jpg',
  '/assets/puzzle-artifacts/dragon-square-paper.jpg',
  'A square sheet of imperial paper decorated with a white dragon playing with a flaming pearl on a red ground. It was reserved for the emperor and associated with highly formal writing occasions.',
  '龙纹斗方纸为皇帝专用纸，纸张呈正方形，红地绘白龙戏珠图案，中央饰火珠，装饰庄严华贵。',
  'During major festivals, the emperor used this kind of paper to write auspicious characters such as “fortune” and “longevity” before bestowing them upon officials. The paper itself therefore carried ceremonial and political meaning.',
  '逢年过节时，皇帝会在这种纸上书写“福”“寿”等字，赐予大臣，因此它本身就具有礼仪与政治象征意义。',
  'Look for the square format and the dragon surrounding the central pearl.',
  '注意正方形纸幅和中央龙戏珠的构图。',
  'Paper',
  'square, paper, dragon, imperial, red-ground',
  'Imperial China',
  'Painting and Calligraphy Materials'
),
(
  1006,
  'Plain Sancai Brush Pot with Sea-and-Dragon Pattern',
  '素三彩海水龙纹笔筒',
  'Plain Sancai Dragon Brush Pot',
  '素三彩龙纹笔筒',
  '/assets/puzzle-artifacts/sancai-dragon-brush-pot.jpg',
  '/assets/puzzle-artifacts/sancai-dragon-brush-pot.jpg',
  'A Qing dynasty brush pot with straight walls, a thick body, and plain sancai glazes. Two powerful four-clawed dragons soar above waves and colored clouds, creating a vigorous yet elegant composition.',
  '清代素三彩海水龙纹笔筒，器壁直立，胎体厚重，外壁刻画双龙戏珠、海水和彩云纹饰，两条四爪行龙飞腾其上，气势雄健。',
  'Plain sancai is named because red is not the dominant color. The vessel was first incised and high-fired as plain porcelain, then covered with selected glazes and low-fired again, demonstrating refined Qing ceramic technique.',
  '素三彩因不以红色为主色而得名。此类器物先在坯胎上刻画纹饰并高温烧成素瓷，再施釉低温复烧，体现了清代彩瓷工艺的成熟水平。',
  'Look for the cylindrical brush pot with dragons moving across waves.',
  '注意圆筒形笔筒，以及海水之上腾跃的龙纹。',
  'Porcelain',
  'cylindrical, dragon, waves, brush-pot, sancai',
  'Qing',
  'Ceramics'
),
(
  1007,
  'Lion-Pattern Yangsui',
  '狮纹阳燧',
  'Lion-Pattern Yangsui',
  '狮纹阳燧',
  '/assets/puzzle-artifacts/lion-pattern-yangsui.jpg',
  '/assets/puzzle-artifacts/lion-pattern-yangsui.jpg',
  'A Tang dynasty square yangsui fire mirror with a sunken central basin, raised rings, four running lions, floral fillers, and four corner knobs for suspension. Its polished reverse was used to focus sunlight for fire-making.',
  '唐代狮纹阳燧，器作方形如镜，中央微凹成圆池，外有重轮，四边各饰一只奔狮，空隙间填花纹，四角有圆纽可供穿系。',
  'Yangsui mirrors are recorded in many ancient texts as devices for obtaining fire from sunlight. Tang examples are relatively rare, so this object is especially valuable for the study of ancient science and technology.',
  '阳燧在古代文献中常见记载，是借助日光取火的器具。唐代实物发现较少，因此这件文物对研究古代科技史具有重要价值。',
  'Notice the square outline, the central round depression, and the four lions.',
  '注意方形外轮廓、中央圆池和四周的奔狮纹。',
  'Bronze',
  'square, mirror, lion, fire-making, ritual-tool',
  'Tang',
  'Metalwork'
),
(
  1008,
  'Cornus-Pattern Embroidered Silk',
  '茱萸纹绣绢',
  'Cornus-Pattern Embroidered Silk',
  '茱萸纹绣绢',
  '/assets/puzzle-artifacts/cornus-embroidered-silk.jpg',
  '/assets/puzzle-artifacts/cornus-embroidered-silk.jpg',
  'A Western Han embroidered silk fragment with cornel patterning, representing the richness of Han textile design, color, and workmanship.',
  '西汉茱萸纹绣绢，纹样细密且富装饰性，是汉代纺织与刺绣工艺水平的重要代表。',
  'The Han textile industry was highly developed, especially in silk production. Luxurious fabrics excavated from Han tombs reveal an exceptional range of colors, patterns, and techniques, and they also reflect the prosperity of Silk Road exchange.',
  '汉代纺织业高度发达，尤其丝绸生产技术先进。汉墓中出土的大量精美织绣品展现出丰富的色彩、纹样和工艺，也反映了丝绸之路贸易的繁荣。',
  'Look for the close-up textile surface with repeating dark branching motifs.',
  '注意织物表面反复出现的深色枝蔓状纹样。',
  'Textile',
  'silk, embroidery, pattern, textile, fabric',
  'Western Han',
  'Textiles'
),
(
  1009,
  'Blue-and-White Jar with Flower-and-Bird Pattern',
  '青花花鸟纹缸',
  'Blue-and-White Flower-and-Bird Jar',
  '青花花鸟纹缸',
  '/assets/puzzle-artifacts/blue-white-flower-bird-jar.jpg',
  '/assets/puzzle-artifacts/blue-white-flower-bird-jar.jpg',
  'A Qing dynasty blue-and-white jar with a flaring mouth, deep body, and ring foot. Its exterior shows rocks, bamboo, peony, plum blossom, and magpies in a continuous scene rendered with layered cobalt tones.',
  '清代青花花鸟纹缸，撇口，深腹，圈足，外壁通景绘洞石、翠竹、牡丹、梅花与喜鹊，青花层次丰富，画面高雅细腻。',
  'The imagery carries auspicious meaning: magpies symbolize good news, while scholar’s rocks suggest elegance and refined taste. The decorative method of deep cobalt outlines with lighter washes was pioneered in the Kangxi period and continued into Yongzheng.',
  '画面寓意吉祥，喜鹊象征喜事来临，洞石则增添文人雅趣。以浓青勾勒、淡色晕染的装饰方法创始于康熙，并延续到雍正时期。',
  'Find the large blue-and-white vessel with birds among blossoms and rocks.',
  '注意大件青花器物上花鸟、洞石与花枝交织的画面。',
  'Porcelain',
  'blue-and-white, jar, bird, floral, scholar-rock',
  'Qing',
  'Ceramics'
),
(
  1010,
  'Offering and Kitchen Pottery Figurines',
  '献食陶俑、庖厨陶俑',
  'Offering and Kitchen Pottery Figurines',
  '献食与庖厨陶俑',
  '/assets/puzzle-artifacts/offering-and-kitchen-figurines.jpg',
  '/assets/puzzle-artifacts/offering-and-kitchen-figurines.jpg',
  'Two Eastern Han pottery figurines showing servants at work: one presents food while the other prepares it. Together they vividly reflect domestic labor within elite households.',
  '东汉献食陶俑与庖厨陶俑，两件作品分别表现端献食物与庖厨劳作的场景，生动再现了豪强家庭中奴婢劳动的真实状态。',
  'Large Eastern Han households often controlled many servants and dependents who performed demanding tasks. Rather than grand ritual poses, these figurines preserve the everyday social reality of labor in aristocratic homes.',
  '东汉豪强大户往往拥有大量奴婢和依附农民，他们承担着繁重的劳动。这两件陶俑没有采用庄严仪式姿态，而是以日常动作记录了当时社会生活的真实面貌。',
  'Look for the paired worker figures shown side by side.',
  '注意并列出现的两件劳作人物俑形象。',
  'Figurine',
  'figurine, servant, offering, kitchen, labor',
  'Eastern Han',
  'Ceramics'
);

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
