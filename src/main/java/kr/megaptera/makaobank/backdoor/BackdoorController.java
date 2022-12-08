package kr.megaptera.makaobank.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/backdoor")
@Transactional
public class BackdoorController {
    private JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;

    public BackdoorController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/setup-user")
    public String setUpUser() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.execute("DELETE FROM person");

        jdbcTemplate.update("INSERT INTO person(" +
                        "  id, name, username, password," +
                        "  amount, created_at, updated_at" +
                        ")" +
                        " VALUES(1, ?, ?, ?, ?, ?, ?)",
                "전제나", "Test1", passwordEncoder.encode("Test123!"),
                5_000_000, now, now
        );

        jdbcTemplate.update("INSERT INTO person(" +
                        "  id, name, username, password," +
                        "  amount, created_at, updated_at" +
                        ")" +
                        " VALUES(2, ?, ?, ?, ?, ?, ?)",
                "강보니", "Test2", passwordEncoder.encode("Test123!"),
                5_000_000, now, now
        );

        return "OK";
    }

    @GetMapping("/reset-products")
    public String resetProducts() {
        jdbcTemplate.execute("DELETE FROM product");

        return "OK";
    }

    @GetMapping("/setup-products")
    public String setUpProducts() {
        jdbcTemplate.execute("DELETE FROM product");

        jdbcTemplate.update("INSERT INTO product" +
                "(id, name, maker, price, description, image_Url) " +
                "VALUES(1,'로엔 LED모션베드 SS(천연라텍스폼)','파로마','669750','로엔 LED모션베드 SS(천연라텍스폼)','https://img.danawa.com/prod_img/500000/569/482/img/6482569_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (2,'라클라우드 이지 모션베드 Q(20cm천연라텍스)','바디프랜드','2690200','라클라우드 이지 모션베드 Q(20cm천연라텍스)','https://img.danawa.com/prod_img/500000/771/641/img/10641771_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (3,'플레인 모션베드 SS(메모리폼)','한샘','1009890','플레인 모션베드 SS(메모리폼)','https://img.danawa.com/prod_img/500000/941/400/img/7400941_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (4,'슬로우베드 모션 매트리스 SS(메모리폼)','일룸','1704200','슬로우베드 모션 매트리스 SS(메모리폼)','https://img.danawa.com/prod_img/500000/591/986/img/5986591_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (5,'다나 캐주얼 책상형 모션베드 SS(천연라텍스)','일룸','2090000','다나 캐주얼 책상형 모션베드 SS(천연라텍스)','https://img.danawa.com/prod_img/500000/050/751/img/14751050_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (6,'루나 모션베드 SS(젤메모리폼)','핸슨','1640000','루나 모션베드 SS(젤메모리폼)','https://img.danawa.com/prod_img/500000/947/336/img/18336947_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (7,'에르고 어스파이어 모션베드 Q(매트별도)','템퍼','1564920','에르고 어스파이어 모션베드 Q(매트별도)','https://img.danawa.com/prod_img/500000/914/787/img/8787914_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (8,'그레슬린 모션베드 SS(매트별도)','쎄라페딕','3954380','그레슬린 모션베드 SS(매트별도)','https://img.danawa.com/prod_img/500000/421/211/img/18211421_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (9,'LED 서울생활 서울불멍 미니','혀니별','13200','LED 서울생활 서울불멍 미니','https://img.danawa.com/prod_img/500000/484/184/img/18184484_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (10,'눈 내리는 겨울 불멍 벽난로 무드등','아스파시아','27900','눈 내리는 겨울 불멍 벽난로 무드등','https://img.danawa.com/prod_img/500000/303/587/img/15587303_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (11,'LED 불멍 대형 불타는 무드등 E','빛내는사람들','34890','LED 불멍 대형 불타는 무드등 E','https://img.danawa.com/prod_img/500000/127/238/img/18238127_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (12,'LED 벽난로 불멍 무드등','모리다인','39770','LED 벽난로 불멍 무드등','https://img.danawa.com/prod_img/500000/109/184/img/18184109_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (13,'LED 라탄볼 전구 10구','인블룸','3500','LED 라탄볼 전구 10구','https://img.danawa.com/prod_img/500000/450/578/img/15578450_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (14,'캥거 LED 빅볼 왕 큰 앵두 전구 10구','델로나','4800','캥거 LED 빅볼 왕 큰 앵두 전구 10구','https://img.danawa.com/prod_img/500000/212/675/img/15675212_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (15,'LED 메리메리 앵두전구 20구','아이씨유','5950','LED 메리메리 앵두전구 20구','https://img.danawa.com/prod_img/500000/439/712/img/15712439_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (16,'LED 앵두전구 50구 OL-CR50','OMT','6710','LED 앵두전구 50구 OL-CR50','https://img.danawa.com/prod_img/500000/680/951/img/15951680_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (17,'LED 선셋 무드 조명','가온에이앤씨','8010','LED 선셋 무드 조명','https://img.danawa.com/prod_img/500000/085/084/img/16084085_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (18,'캥거 LED 만달라키 선셋 무드등','델로나','8800','캥거 LED 만달라키 선셋 무드등','https://img.danawa.com/prod_img/500000/518/664/img/14664518_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (19,'LED 3D 행성 무드등','홈즈','24500','LED 3D 행성 무드등','https://img.danawa.com/prod_img/500000/675/073/img/16073675_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (20,'LED 오로라 무드등','홈즈','45890','LED 오로라 무드등','https://img.danawa.com/prod_img/500000/102/262/img/16262102_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (21,'오키 LED 슬림 벽난로 콘솔 수납장 장식장','나누벨','103900','오키 LED 슬림 벽난로 콘솔 수납장 장식장','https://img.danawa.com/prod_img/500000/955/887/img/17887955_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (22,'보테닉 LED 벽난로 콘솔 장식장(80cm)','마레앤코','129000','보테닉 LED 벽난로 콘솔 장식장(80cm)','https://img.danawa.com/prod_img/500000/725/375/img/15375725_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (23,'로앤 벽난로 장식장','오목샘','75000','로앤 벽난로 장식장','https://img.danawa.com/prod_img/500000/555/695/img/15695555_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (24,'벽난로 콘솔 아치형 장식 선반장 700','퍼피노','149000','벽난로 콘솔 아치형 장식 선반장 700','https://img.danawa.com/prod_img/500000/791/883/img/17883791_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (25,'트리니홈 LED 벽난로 콘솔 아치형 장식장 L','우림가구','123900','트리니홈 LED 벽난로 콘솔 아치형 장식장 L','https://img.danawa.com/prod_img/500000/233/922/img/14922233_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (26,'딕시 벽난로 콘솔 수납장 선반 장식장','가구의가치','53600','딕시 벽난로 콘솔 수납장 선반 장식장','https://img.danawa.com/prod_img/500000/736/887/img/17887736_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (27,'멜론 템바보드 벽난로 콘솔 장식장 800','잉글랜더','109900','멜론 템바보드 벽난로 콘솔 장식장 800','https://img.danawa.com/prod_img/500000/139/929/img/17929139_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (28,'비스킷 LED 벽난로 콘솔 1000','오트밀하우스','119000','비스킷 LED 벽난로 콘솔 1000','https://img.danawa.com/prod_img/500000/390/811/img/16811390_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (29,'자가발열 캔들 토퍼(퀸 Q)','쉬즈홈','40850','자가발열 캔들 토퍼(퀸 Q)','https://img.danawa.com/prod_img/500000/890/261/img/18261890_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (30,'마약 웜매트(퀸 Q)','바디럽','50220','마약 웜매트(퀸 Q)','https://img.danawa.com/prod_img/500000/067/545/img/6545067_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (31,'콤피 발열 극세사 차렵이불(퀸 Q)','올리비아데코','43940','콤피 발열 극세사 차렵이불(퀸 Q)','https://img.danawa.com/prod_img/500000/648/257/img/18257648_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (32,'투웨이 자가발열 양면 차렵이불(퀸 Q)','올리비아데코','40210','투웨이 자가발열 양면 차렵이불(퀸 Q)','https://img.danawa.com/prod_img/500000/209/681/img/15681209_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (33,'말캉감촉 자가발열 차렵이불(퀸 Q)','벨라이프','89980','말캉감촉 자가발열 차렵이불(퀸 Q)','https://img.danawa.com/prod_img/500000/776/147/img/18147776_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (34,'자가발열 히트온 차렵이불(퀸 Q)','포그나미','65480','자가발열 히트온 차렵이불(퀸 Q)','https://img.danawa.com/prod_img/500000/362/630/img/15630362_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (35,'말캉감촉 자가발열 밴딩 논슬립패드(퀸 Q/킹 K)','벨라이프','54950','말캉감촉 자가발열 밴딩 논슬립패드(퀸 Q/킹 K)','https://img.danawa.com/prod_img/500000/599/147/img/18147599_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (36,'극세사 웜패드(퀸 Q)','바디럽','83700','극세사 웜패드(퀸 Q)','https://img.danawa.com/prod_img/500000/652/208/img/18208652_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (37,'리진 포시즌 양모이불(퀸 Q)','자연앤리빙','37400','리진 포시즌 양모이불(퀸 Q)','https://img.danawa.com/prod_img/500000/016/076/img/18076016_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (38,'포근한 양모 이불솜(퀸 Q)','나다니엘','46070','포근한 양모 이불솜(퀸 Q)','https://img.danawa.com/prod_img/500000/272/174/img/18174272_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (39,'프리미엄 워셔블 양모 이불솜(퀸 Q)','엘르파리','99160','프리미엄 워셔블 양모 이불솜(퀸 Q)','https://img.danawa.com/prod_img/500000/149/174/img/18174149_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (40,'호주산 프리미엄 양모 이불솜(퀸 Q)','모노데코','112520','호주산 프리미엄 양모 이불솜(퀸 Q)','https://img.danawa.com/prod_img/500000/902/075/img/18075902_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (41,'에어울 호주산 양모이불솜(퀸 Q)','오트몬드','127290','에어울 호주산 양모이불솜(퀸 Q)','https://img.danawa.com/prod_img/500000/888/074/img/18074888_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (42,'헬렌스타인 투인원 호주산 양모이불(퀸 Q)','리디아알앤씨','161700','헬렌스타인 투인원 호주산 양모이불(퀸 Q)','https://img.danawa.com/prod_img/500000/425/650/img/16650425_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (43,'카라반 양모 이불솜(퀸 Q)','아포아룸','210000','카라반 양모 이불솜(퀸 Q)','https://img.danawa.com/prod_img/500000/416/174/img/18174416_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (44,'크리스피 양모이불솜 1kg(퀸 Q)','크리스피바바','275990','크리스피 양모이불솜 1kg(퀸 Q)','https://img.danawa.com/prod_img/500000/843/173/img/18173843_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (45,'대형 블루밍 B807 빈백 1인용','보니타','77160','대형 블루밍 B807 빈백 1인용','https://img.danawa.com/prod_img/500000/287/539/img/16539287_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (46,'607C 빈백 1인용(기본속커버, 스툴포함)','폴리몰리','236550','607C 빈백 1인용(기본속커버, 스툴포함)','https://img.danawa.com/prod_img/500000/804/000/img/7000804_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (47,'그랜드 빈백 1인용(스툴포함)','리브맘','164680','그랜드 빈백 1인용(스툴포함)','https://img.danawa.com/prod_img/500000/464/683/img/13683464_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (48,'맥스 오리지널 빈백 1인용','요기보','311200','맥스 오리지널 빈백 1인용','https://img.danawa.com/prod_img/500000/794/641/img/5641794_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (49,'플래지어 쿤 릴렉스 핏 기능성 가죽 빈백 1인용(스툴포함)','까사미아','250850','플래지어 쿤 릴렉스 핏 기능성 가죽 빈백 1인용(스툴포함)','https://img.danawa.com/prod_img/500000/511/701/img/17701511_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (50,'버터플라이 빈백 1인용','엠비언트라운지','181370','버터플라이 빈백 1인용','https://img.danawa.com/prod_img/500000/110/258/img/2258110_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (51,'맥스 하이브리드 빈백 1인용','요기보','432440','맥스 하이브리드 빈백 1인용','https://img.danawa.com/prod_img/500000/920/536/img/16536920_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (52,'특대 마이크로 에어볼 빈백 1인용','벨라홈','61990','특대 마이크로 에어볼 빈백 1인용','https://img.danawa.com/prod_img/500000/687/722/img/15722687_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (53,'이즈 비안나 헤드틸팅 아쿠아 패브릭 소파 3인용','동서가구','243040','이즈 비안나 헤드틸팅 아쿠아 패브릭 소파 3인용','https://img.danawa.com/prod_img/500000/842/736/img/17736842_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (54,'헤일리 아쿠아텍스 패브릭 소파 3인용(스툴포함)','삼익가구','189140','헤일리 아쿠아텍스 패브릭 소파 3인용(스툴포함)','https://img.danawa.com/prod_img/500000/049/543/img/13543049_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (55,'티렌토 리세 아쿠아텍스 패브릭 소파 3인용(스툴포함)','한샘몰','307700','티렌토 리세 아쿠아텍스 패브릭 소파 3인용(스툴포함)','https://img.danawa.com/prod_img/500000/017/581/img/13581017_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (56,'캄포 클래식 패브릭 소파 4인용(스툴별도)','까사미아','1851300','캄포 클래식 패브릭 소파 4인용(스툴별도)','https://img.danawa.com/prod_img/500000/009/324/img/9324009_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (57,'DK096 부클레 패브릭 소파 3.5인용(스툴별도)','듀커','790900','DK096 부클레 패브릭 소파 3.5인용(스툴별도)','https://img.danawa.com/prod_img/500000/427/929/img/17929427_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (58,'부클레 패브릭 소파 3인용','퍼피노','458470','부클레 패브릭 소파 3인용','https://img.danawa.com/prod_img/500000/068/991/img/17991068_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (59,'DK230 부클레 패브릭 소파 3.5인용','듀커','890900','DK230 부클레 패브릭 소파 3.5인용','https://img.danawa.com/prod_img/500000/202/929/img/17929202_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (60,'미뇽C 양털 부클레 패브릭 소파 3인용','웰퍼니쳐','293190','미뇽C 양털 부클레 패브릭 소파 3인용','https://img.danawa.com/prod_img/500000/314/928/img/17928314_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (61,'우스터 천연가죽 리클라이너 1인용(스툴포함)','까사미아','838300','우스터 천연가죽 리클라이너 1인용(스툴포함)','https://img.danawa.com/prod_img/500000/786/842/img/5842786_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (62,'메를로 패브릭 리클라이너 1인용(스툴포함)','한샘','111860','메를로 패브릭 리클라이너 1인용(스툴포함)','https://img.danawa.com/prod_img/500000/205/628/img/15628205_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (63,'올레스트 패브릭 리클라이너 1인용(스툴포함)','수오','109000','올레스트 패브릭 리클라이너 1인용(스툴포함)','https://img.danawa.com/prod_img/500000/748/532/img/10532748_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (64,'로빈 맥스트라이 조야 패브릭 리클라이너 1인용','보니애가구','233770','로빈 맥스트라이 조야 패브릭 리클라이너 1인용','https://img.danawa.com/prod_img/500000/007/669/img/16669007_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (65,'앵글러 접이식 암체어 패브릭 의자 1인용','한샘','67900','앵글러 접이식 암체어 패브릭 의자 1인용','https://img.danawa.com/prod_img/500000/914/707/img/13707914_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (66,'휴 회전형 스마트 패브릭 좌식소파 1인용','한샘','109160','휴 회전형 스마트 패브릭 좌식소파 1인용','https://img.danawa.com/prod_img/500000/337/745/img/15745337_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (67,'릴렉스 접이식 안락의자 1인용','로제까사','56070','릴렉스 접이식 안락의자 1인용','https://img.danawa.com/prod_img/500000/559/220/img/6220559_1.jpg??shrink=360:360&_v=20221130213722'),\n"
                + "  (68,'토리 흔들 의자 1인용(스툴포함)','가즈다가구','165000','토리 흔들 의자 1인용(스툴포함)','https://img.danawa.com/prod_img/500000/687/296/img/18296687_1.jpg??shrink=360:360&_v=20221130213722')\n");

        return "OK";
    }
}
