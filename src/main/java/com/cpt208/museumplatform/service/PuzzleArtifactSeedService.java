package com.cpt208.museumplatform.service;

import com.cpt208.museumplatform.entity.PuzzleArtifactEntity;
import com.cpt208.museumplatform.repository.PuzzleArtifactRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuzzleArtifactSeedService {

    private final PuzzleArtifactRepository puzzleArtifactRepository;

    public PuzzleArtifactSeedService(PuzzleArtifactRepository puzzleArtifactRepository) {
        this.puzzleArtifactRepository = puzzleArtifactRepository;
    }

    @PostConstruct
    public void seedPuzzleArtifacts() {
        if (puzzleArtifactRepository.count() > 0) {
            return;
        }

        puzzleArtifactRepository.saveAll(List.of(
            new PuzzleArtifactEntity(
                1001L,
                "Jade Booklet",
                "玉册",
                "Jade Booklet",
                "玉册",
                "/assets/puzzle-artifacts/jade-booklet.jpg",
                "/assets/puzzle-artifacts/jade-booklet.jpg",
                "A Ming dynasty jade booklet carved from pale celadon-white jade with dark brown mottling. Flat and slightly trapezoidal, it is engraved with imperial inscription text and framed by dragons, phoenixes, and waves in a highly ceremonial design.",
                "明代青白玉玉册，玉色间有黑褐色沁色，体扁平呈长梯形。两面刻有册命文字与龙凤、海水等纹饰，整体华丽工整，是皇家册封礼制的重要实物。",
                "The inscription quotes words once used by emperors to reward meritorious officials. The term ce ming refers to solemn imperial commands used in major acts of investiture and state ritual.",
                "器上文字引自古代帝王封赏功臣之语，“册命”则指帝王用于告祭天地宗庙、册立太子皇后及诸王大臣的正式命令，后成为册封礼制的重要象征。",
                "Look for the trapezoidal jade shape and the carved dragon motifs around the inscription.",
                "注意长梯形玉板外形，以及文字上方和四周的龙凤纹饰。",
                "Jade",
                "jade, carved, trapezoid, inscription",
                "Ming",
                "Ceremonial Objects"
            ),
            new PuzzleArtifactEntity(
                1002L,
                "Proto-Porcelain Dou",
                "原始瓷豆",
                "Proto-Porcelain Dou",
                "原始瓷豆",
                "/assets/puzzle-artifacts/proto-porcelain-dou.jpg",
                "/assets/puzzle-artifacts/proto-porcelain-dou.jpg",
                "A Western Zhou proto-porcelain dou with a shallow dish, thick stem, and flaring ring foot. Its greenish glaze marks an early stage in porcelain development and reflects improved ceramic technology after the Shang period.",
                "西周原始瓷豆，敛口浅腹，粗柄，圈足侈大，通体施青釉。它体现了西周时期原始瓷器工艺的提升，是中国早期瓷器发展过程中的代表器物。",
                "Proto-porcelain became richer in shape and more widely distributed during the Western Zhou. Southern regions yielded especially large numbers and diverse examples of these early glazed wares.",
                "西周时期原始瓷器比商代器形更丰富、工艺更进步，出土范围也更加广泛，尤其南方地区发现数量多、器类丰富，显示出早期瓷器发展的重要阶段。",
                "Look for the raised stem and broad circular foot beneath the shallow dish.",
                "观察浅盘下方粗壮的柄部和外撇的圈足。",
                "Ceramic",
                "bowl, stem, ring-foot, glaze",
                "Western Zhou",
                "Ceramics"
            ),
            new PuzzleArtifactEntity(
                1003L,
                "Celadon Zodiac Figurines of Rat, Ox, and Monkey",
                "青瓷鼠、牛、猴俑",
                "Celadon Zodiac Figurines",
                "青瓷生肖俑",
                "/assets/puzzle-artifacts/celadon-zodiac-figurines.jpg",
                "/assets/puzzle-artifacts/celadon-zodiac-figurines.jpg",
                "Three Sui dynasty celadon zodiac figurines representing the rat, ox, and monkey. Each has a human body, animal head, official robe, and seated pose, with refined modeling and a yellow-green glaze.",
                "隋代青瓷十二生肖俑中的三件，分别为鼠、牛、猴。它们都具有人身兽首、着衣持笏、盘坐的形态，釉色青黄，造型生动精致，艺术价值很高。",
                "Although zodiac beliefs are often linked to later texts, references already existed earlier. Figurines shaped as zodiac attendants, however, are understood to have appeared from the Sui dynasty onward.",
                "十二生肖观念并非始于东汉，而更早文献中已见记载；但以这种俑的形式集中表现生肖形象，则一般认为始于隋代。",
                "Notice the combination of human robes and animal heads in three seated figures.",
                "注意三件俑都为人身着衣、兽首盘坐的组合形态。",
                "Figurine",
                "figurine, zodiac, celadon, seated",
                "Sui",
                "Ceramics"
            ),
            new PuzzleArtifactEntity(
                1004L,
                "Dragon-Pattern Jade Bi",
                "龙纹玉璧",
                "Dragon-Pattern Jade Bi",
                "龙纹玉璧",
                "/assets/puzzle-artifacts/dragon-jade-bi.jpg",
                "/assets/puzzle-artifacts/dragon-jade-bi.jpg",
                "A Western Han jade bi disk, 26.3 cm in diameter, decorated with fine grain patterning and a ring of stylized dragon motifs along the outer edge. Its polished surface reflects the high level of Han jade craftsmanship.",
                "西汉龙纹玉璧，直径26.3厘米，璧面布满细密谷纹，外缘饰一周图案化龙纹。玉质坚硬晶莹，工艺精细，是汉代玉器制作水平的代表之一。",
                "Jade bi disks had long ritual associations. By the Han dynasty their ceremonial role was declining, yet they still appeared in grand sacrifices and burials, often placed on the body or coffin as sacred and decorative objects.",
                "璧在先秦属于祭玉和瑞玉，到汉代祭祀功能虽趋衰落，但在祭天祭川和墓葬中仍被广泛使用，可置于死者胸背或棺椁中，兼具礼仪与随葬意义。",
                "Find the round jade disk with a central hole and tightly packed grain motifs.",
                "注意中央穿孔的圆形玉璧，以及密集整齐的谷纹。",
                "Jade",
                "round, jade, disk, dragon",
                "Western Han",
                "Jade and Stone"
            ),
            new PuzzleArtifactEntity(
                1005L,
                "Dragon-Pattern Square Paper",
                "龙纹斗方纸",
                "Dragon-Pattern Square Paper",
                "龙纹斗方纸",
                "/assets/puzzle-artifacts/dragon-square-paper.jpg",
                "/assets/puzzle-artifacts/dragon-square-paper.jpg",
                "A square sheet of imperial paper decorated with a white dragon playing with a flaming pearl on a red ground. The design marks it as paper reserved for the emperor.",
                "龙纹斗方纸，纸面正方，红地绘白龙戏珠图案，中央饰火珠，是皇帝专用纸张，纹样庄严华丽。",
                "During major festivals the emperor used this paper to write auspicious characters such as fu and shou before bestowing them upon high officials, making the paper itself part of imperial ceremonial exchange.",
                "逢年过节时，皇帝会在这种纸上书写“福”“寿”等字，赐予文武大臣，因此它不仅是书写材料，也是皇家礼仪的一部分。",
                "Identify the square format and the central dragon around a flaming pearl.",
                "观察它的斗方形制，以及中央龙戏珠的构图。",
                "Paper",
                "square, dragon, paper, imperial",
                "Imperial China",
                "Painting and Calligraphy Materials"
            ),
            new PuzzleArtifactEntity(
                1006L,
                "Plain Sancai Brush Pot with Sea-and-Dragon Pattern",
                "素三彩海水龙纹笔筒",
                "Plain Sancai Dragon Brush Pot",
                "素三彩龙纹笔筒",
                "/assets/puzzle-artifacts/sancai-dragon-brush-pot.jpg",
                "/assets/puzzle-artifacts/sancai-dragon-brush-pot.jpg",
                "A Qing dynasty brush pot with straight walls and thick body, decorated in plain sancai glazes. Two powerful four-clawed dragons soar above waves and colored clouds, creating a vigorous and refined composition.",
                "清代素三彩海水龙纹笔筒，器壁直立，胎体厚重，外壁绘双龙戏珠、海水和彩云纹。两条四爪行龙昂首飞腾于波涛之上，气势雄健，是康熙素三彩精品。",
                "So-called plain sancai omits red as the dominant color. The vessel was first fired as plain porcelain after black-line carving, then coated with selected low-temperature glazes, showing the technical sophistication of Qing enamel practice.",
                "素三彩因以不用红色为主而得名。此类器物先在坯上刻划纹饰并高温烧成素瓷，再施釉低温复烧，既体现了清代彩瓷工艺，也保留了刻线装饰的活力。",
                "Look for a cylindrical brush pot with dragons moving above stylized waves.",
                "注意笔筒的圆筒形体和外壁海水间飞动的龙纹。",
                "Porcelain",
                "cylindrical, dragon, waves, sancai",
                "Qing",
                "Ceramics"
            ),
            new PuzzleArtifactEntity(
                1007L,
                "Lion-Pattern Yangsui",
                "狮纹阳燧",
                "Lion-Pattern Yangsui",
                "狮纹阳燧",
                "/assets/puzzle-artifacts/lion-pattern-yangsui.jpg",
                "/assets/puzzle-artifacts/lion-pattern-yangsui.jpg",
                "A Tang dynasty square yangsui fire mirror with a slightly sunken central basin, raised rings, four vigorous lions, floral fillers, and four corner knobs for suspension. Its polished reverse was used to harness sunlight for fire-making.",
                "唐代狮纹阳燧，器方形如镜，中心微凹成圆池，外有重轮，四边各饰一只奔狮，空隙间填五瓣花纹，四角有圆纽可供穿系。另一面光素，可借日光取火。",
                "Yangsui mirrors are recorded in many ancient texts as devices for obtaining fire from sunlight. Tang examples are rare, making this object especially valuable for studying ancient scientific and technical practice.",
                "阳燧在古代文献中常见记载，是借助日光取火的器具。唐代实物发现较少，因此这件狮纹阳燧对于研究古代科技史具有重要意义。",
                "Notice the square outline, central round depression, and four running lions.",
                "注意方形外轮廓、中央圆池和四边奔狮纹。",
                "Bronze",
                "square, mirror, lion, fire-making",
                "Tang",
                "Metalwork"
            ),
            new PuzzleArtifactEntity(
                1008L,
                "Cornus-Pattern Embroidered Silk",
                "茱萸纹绣绢",
                "Cornus-Pattern Embroidered Silk",
                "茱萸纹绣绢",
                "/assets/puzzle-artifacts/cornus-embroidered-silk.jpg",
                "/assets/puzzle-artifacts/cornus-embroidered-silk.jpg",
                "A Western Han embroidered silk fragment with cornel patterning, representing the extraordinary richness of Han textile design, color, and workmanship.",
                "西汉茱萸纹绣绢，纹样细密而富装饰性，是汉代高水平丝织与刺绣工艺的代表性遗存之一。",
                "The Han textile industry was highly advanced, especially in silk production. Finds from the Mawangdui tombs show the remarkable range of woven and embroidered fabrics in use, and such textiles also underpinned the prosperity of the Silk Road.",
                "西汉纺织业高度发达，丝绸生产技术先进、品种丰富。马王堆汉墓出土的大量丝织品充分展现了汉代织绣工艺的高度成就，也反映出丝绸之路繁荣的物质基础。",
                "Look for the close-up textile image with dark branching motifs on a warm silk ground.",
                "观察暖色绢地上深色枝蔓式纹样的近景织物细节。",
                "Textile",
                "silk, embroidery, pattern, textile",
                "Western Han",
                "Textiles"
            ),
            new PuzzleArtifactEntity(
                1009L,
                "Blue-and-White Jar with Flower-and-Bird Pattern",
                "青花花鸟纹缸",
                "Blue-and-White Flower-and-Bird Jar",
                "青花花鸟纹缸",
                "/assets/puzzle-artifacts/blue-white-flower-bird-jar.jpg",
                "/assets/puzzle-artifacts/blue-white-flower-bird-jar.jpg",
                "A Qing dynasty blue-and-white jar with flaring mouth, deep body, and ring foot. Its exterior shows rocks, bamboo, peony, plum blossom, and magpies in a continuous scene rendered with layered cobalt tones.",
                "清代青花花鸟纹缸，撇口深腹，平底圈足，外壁通景绘洞石、翠竹、牡丹、梅花与喜鹊。青花色彩有浓有淡，营造出高雅而细致的画面效果。",
                "The magpie and scholar's rock create an auspicious pun of joy arriving at all times. This mode of using rich cobalt outlines with pale washes was pioneered in the Kangxi era and continued in Yongzheng, blending popular symbolism with literati elegance.",
                "喜鹊与洞石形成“时时报喜”的吉祥寓意。以浓青勾描、淡色晕染的装饰技法创始于康熙并延续于雍正，通俗题材以清雅形式呈现，别具文人画意味。",
                "Find the large blue-and-white vessel with birds among blossoms and rocks.",
                "注意青花器身上花鸟、洞石与梅枝交织的通景画面。",
                "Porcelain",
                "blue-and-white, jar, bird, floral",
                "Qing",
                "Ceramics"
            ),
            new PuzzleArtifactEntity(
                1010L,
                "Offering and Kitchen Pottery Figurines",
                "献食陶俑、庖厨陶俑",
                "Offering and Kitchen Pottery Figurines",
                "献食与庖厨陶俑",
                "/assets/puzzle-artifacts/offering-and-kitchen-figurines.jpg",
                "/assets/puzzle-artifacts/offering-and-kitchen-figurines.jpg",
                "Two Eastern Han pottery figurines showing servants at work: one presents food while the other prepares it. Together they vividly reflect labor within elite households of the period.",
                "东汉献食陶俑与庖厨陶俑，两件作品分别表现端献食物与庖厨操作场景，生动再现了豪强家庭中奴婢劳作的真实状态。",
                "Large Eastern Han households often controlled many dependents and servants who undertook demanding work. These figurines preserve that social reality through everyday domestic action rather than grand ritual posture.",
                "东汉豪强大户往往拥有大量奴婢和依附农民，他们承担着繁重劳动。这两件陶俑没有采用庄严祭祀姿态，而是以日常动作记录了当时社会生活的真实面貌。",
                "Look for the paired figures with trays and kitchen tools shown together.",
                "注意两件人物俑并列呈现，一件献食，一件庖厨操作。",
                "Figurine",
                "figurine, servant, offering, kitchen",
                "Eastern Han",
                "Ceramics"
            )
        ));
    }
}
