package cdm.base.datetime;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the business centers.
 * @version ${project.version}
 *
 * Body ISDA
 * Corpus Scheme FpML_Coding_Scheme   
 * schemeLocation "http://www.fpml.org/coding-scheme/business-center"
 *
 * Provision 
 *
 */
@RosettaEnum("BusinessCenterEnum")
public enum BusinessCenterEnum {

	/**
	 * Abu Dhabi, Business Day (as defined in 2021 ISDA Definitions Section 2.1.10 (ii))
	 */
	@RosettaEnumValue(value = "AEAB") AEAB("AEAB"),
	
	/**
	 * Abu Dhabi, Settlement Day (as defined in 2021 ISDA Definitions Section 2.1.10 (i))
	 */
	@RosettaEnumValue(value = "AEAD") AEAD("AEAD"),
	
	/**
	 * Dubai, United Arab Emirates
	 */
	@RosettaEnumValue(value = "AEDU") AEDU("AEDU"),
	
	/**
	 * Yerevan, Armenia
	 */
	@RosettaEnumValue(value = "AMYE") AMYE("AMYE"),
	
	/**
	 * Luanda, Angola
	 */
	@RosettaEnumValue(value = "AOLU") AOLU("AOLU"),
	
	/**
	 * Buenos Aires, Argentina
	 */
	@RosettaEnumValue(value = "ARBA") ARBA("ARBA"),
	
	/**
	 * Vienna, Austria
	 */
	@RosettaEnumValue(value = "ATVI") ATVI("ATVI"),
	
	/**
	 * Adelaide, Australia
	 */
	@RosettaEnumValue(value = "AUAD") AUAD("AUAD"),
	
	/**
	 * Brisbane, Australia
	 */
	@RosettaEnumValue(value = "AUBR") AUBR("AUBR"),
	
	/**
	 * Canberra, Australia
	 */
	@RosettaEnumValue(value = "AUCA") AUCA("AUCA"),
	
	/**
	 * Darwin, Australia
	 */
	@RosettaEnumValue(value = "AUDA") AUDA("AUDA"),
	
	/**
	 * Melbourne, Australia
	 */
	@RosettaEnumValue(value = "AUME") AUME("AUME"),
	
	/**
	 * Perth, Australia
	 */
	@RosettaEnumValue(value = "AUPE") AUPE("AUPE"),
	
	/**
	 * Sydney, Australia
	 */
	@RosettaEnumValue(value = "AUSY") AUSY("AUSY"),
	
	/**
	 * Baku, Azerbaijan
	 */
	@RosettaEnumValue(value = "AZBA") AZBA("AZBA"),
	
	/**
	 * Bridgetown, Barbados
	 */
	@RosettaEnumValue(value = "BBBR") BBBR("BBBR"),
	
	/**
	 * Dhaka, Bangladesh
	 */
	@RosettaEnumValue(value = "BDDH") BDDH("BDDH"),
	
	/**
	 * Brussels, Belgium
	 */
	@RosettaEnumValue(value = "BEBR") BEBR("BEBR"),
	
	/**
	 * Sofia, Bulgaria
	 */
	@RosettaEnumValue(value = "BGSO") BGSO("BGSO"),
	
	/**
	 * Manama, Bahrain
	 */
	@RosettaEnumValue(value = "BHMA") BHMA("BHMA"),
	
	/**
	 * Hamilton, Bermuda
	 */
	@RosettaEnumValue(value = "BMHA") BMHA("BMHA"),
	
	/**
	 * Bandar Seri Begawan, Brunei
	 */
	@RosettaEnumValue(value = "BNBS") BNBS("BNBS"),
	
	/**
	 * La Paz, Bolivia
	 */
	@RosettaEnumValue(value = "BOLP") BOLP("BOLP"),
	
	/**
	 * Brazil Business Day.
	 */
	@RosettaEnumValue(value = "BRBD") BRBD("BRBD"),
	
	/**
	 * Brasilia, Brazil.
	 */
	@RosettaEnumValue(value = "BRBR") BRBR("BRBR"),
	
	/**
	 * Rio de Janeiro, Brazil.
	 */
	@RosettaEnumValue(value = "BRRJ") BRRJ("BRRJ"),
	
	/**
	 * Sao Paulo, Brazil.
	 */
	@RosettaEnumValue(value = "BRSP") BRSP("BRSP"),
	
	/**
	 * Nassau, Bahamas
	 */
	@RosettaEnumValue(value = "BSNA") BSNA("BSNA"),
	
	/**
	 * Gaborone, Botswana
	 */
	@RosettaEnumValue(value = "BWGA") BWGA("BWGA"),
	
	/**
	 * Minsk, Belarus
	 */
	@RosettaEnumValue(value = "BYMI") BYMI("BYMI"),
	
	/**
	 * Calgary, Canada
	 */
	@RosettaEnumValue(value = "CACL") CACL("CACL"),
	
	/**
	 * Fredericton, Canada.
	 */
	@RosettaEnumValue(value = "CAFR") CAFR("CAFR"),
	
	/**
	 * Montreal, Canada
	 */
	@RosettaEnumValue(value = "CAMO") CAMO("CAMO"),
	
	/**
	 * Ottawa, Canada
	 */
	@RosettaEnumValue(value = "CAOT") CAOT("CAOT"),
	
	/**
	 * Toronto, Canada
	 */
	@RosettaEnumValue(value = "CATO") CATO("CATO"),
	
	/**
	 * Vancouver, Canada
	 */
	@RosettaEnumValue(value = "CAVA") CAVA("CAVA"),
	
	/**
	 * Winnipeg, Canada
	 */
	@RosettaEnumValue(value = "CAWI") CAWI("CAWI"),
	
	/**
	 * Basel, Switzerland
	 */
	@RosettaEnumValue(value = "CHBA") CHBA("CHBA"),
	
	/**
	 * Geneva, Switzerland
	 */
	@RosettaEnumValue(value = "CHGE") CHGE("CHGE"),
	
	/**
	 * Zurich, Switzerland
	 */
	@RosettaEnumValue(value = "CHZU") CHZU("CHZU"),
	
	/**
	 * Abidjan, Cote d&#39;Ivoire
	 */
	@RosettaEnumValue(value = "CIAB") CIAB("CIAB"),
	
	/**
	 * Santiago, Chile
	 */
	@RosettaEnumValue(value = "CLSA") CLSA("CLSA"),
	
	/**
	 * Yaounde, Cameroon
	 */
	@RosettaEnumValue(value = "CMYA") CMYA("CMYA"),
	
	/**
	 * Beijing, China
	 */
	@RosettaEnumValue(value = "CNBE") CNBE("CNBE"),
	
	/**
	 * Shanghai, China
	 */
	@RosettaEnumValue(value = "CNSH") CNSH("CNSH"),
	
	/**
	 * Bogota, Colombia
	 */
	@RosettaEnumValue(value = "COBO") COBO("COBO"),
	
	/**
	 * San Jose, Costa Rica
	 */
	@RosettaEnumValue(value = "CRSJ") CRSJ("CRSJ"),
	
	/**
	 * Willemstad, Curacao
	 */
	@RosettaEnumValue(value = "CWWI") CWWI("CWWI"),
	
	/**
	 * Nicosia, Cyprus
	 */
	@RosettaEnumValue(value = "CYNI") CYNI("CYNI"),
	
	/**
	 * Prague, Czech Republic
	 */
	@RosettaEnumValue(value = "CZPR") CZPR("CZPR"),
	
	/**
	 * Cologne, Germany
	 */
	@RosettaEnumValue(value = "DECO") DECO("DECO"),
	
	/**
	 * Dusseldorf, Germany
	 */
	@RosettaEnumValue(value = "DEDU") DEDU("DEDU"),
	
	/**
	 * Frankfurt, Germany
	 */
	@RosettaEnumValue(value = "DEFR") DEFR("DEFR"),
	
	/**
	 * Hannover, Germany
	 */
	@RosettaEnumValue(value = "DEHA") DEHA("DEHA"),
	
	/**
	 * Hamburg, Germany
	 */
	@RosettaEnumValue(value = "DEHH") DEHH("DEHH"),
	
	/**
	 * Leipzig, Germany
	 */
	@RosettaEnumValue(value = "DELE") DELE("DELE"),
	
	/**
	 * Mainz, Germany
	 */
	@RosettaEnumValue(value = "DEMA") DEMA("DEMA"),
	
	/**
	 * Munich, Germany
	 */
	@RosettaEnumValue(value = "DEMU") DEMU("DEMU"),
	
	/**
	 * Stuttgart, Germany
	 */
	@RosettaEnumValue(value = "DEST") DEST("DEST"),
	
	/**
	 * Copenhagen, Denmark
	 */
	@RosettaEnumValue(value = "DKCO") DKCO("DKCO"),
	
	/**
	 * Santo Domingo, Dominican Republic
	 */
	@RosettaEnumValue(value = "DOSD") DOSD("DOSD"),
	
	/**
	 * Algiers, Algeria
	 */
	@RosettaEnumValue(value = "DZAL") DZAL("DZAL"),
	
	/**
	 * Guayaquil, Ecuador
	 */
	@RosettaEnumValue(value = "ECGU") ECGU("ECGU"),
	
	/**
	 * Tallinn, Estonia
	 */
	@RosettaEnumValue(value = "EETA") EETA("EETA"),
	
	/**
	 * Cairo, Egypt
	 */
	@RosettaEnumValue(value = "EGCA") EGCA("EGCA"),
	
	/**
	 * ESAS Settlement Day (as defined in 2006 ISDA Definitions Section 7.1 and Supplement Number 15 to the 2000 ISDA Definitions)
	 */
	@RosettaEnumValue(value = "ESAS") ESAS("ESAS"),
	
	/**
	 * Barcelona, Spain
	 */
	@RosettaEnumValue(value = "ESBA") ESBA("ESBA"),
	
	/**
	 * Madrid, Spain
	 */
	@RosettaEnumValue(value = "ESMA") ESMA("ESMA"),
	
	/**
	 * San Sebastian, Spain
	 */
	@RosettaEnumValue(value = "ESSS") ESSS("ESSS"),
	
	/**
	 * Addis Ababa, Ethiopia
	 */
	@RosettaEnumValue(value = "ETAA") ETAA("ETAA"),
	
	/**
	 * Publication dates for ICE Swap rates based on EUR-EURIBOR rates
	 */
	@RosettaEnumValue(value = "EUR_ICESWAP", displayName = "EUR-ICESWAP") EUR_ICESWAP("EUR_ICESWAP", "EUR-ICESWAP"),
	
	/**
	 * TARGET Settlement Day
	 */
	@RosettaEnumValue(value = "EUTA") EUTA("EUTA"),
	
	/**
	 * Helsinki, Finland
	 */
	@RosettaEnumValue(value = "FIHE") FIHE("FIHE"),
	
	/**
	 * Paris, France
	 */
	@RosettaEnumValue(value = "FRPA") FRPA("FRPA"),
	
	/**
	 * Edinburgh, Scotland
	 */
	@RosettaEnumValue(value = "GBED") GBED("GBED"),
	
	/**
	 * London, United Kingdom
	 */
	@RosettaEnumValue(value = "GBLO") GBLO("GBLO"),
	
	/**
	 * Publication dates for GBP ICE Swap rates
	 */
	@RosettaEnumValue(value = "GBP_ICESWAP", displayName = "GBP-ICESWAP") GBP_ICESWAP("GBP_ICESWAP", "GBP-ICESWAP"),
	
	/**
	 * Tbilisi, Georgia
	 */
	@RosettaEnumValue(value = "GETB") GETB("GETB"),
	
	/**
	 * Saint Peter Port, Guernsey
	 */
	@RosettaEnumValue(value = "GGSP") GGSP("GGSP"),
	
	/**
	 * Accra, Ghana
	 */
	@RosettaEnumValue(value = "GHAC") GHAC("GHAC"),
	
	/**
	 * Gibraltar, Gibraltar
	 */
	@RosettaEnumValue(value = "GIGI") GIGI("GIGI"),
	
	/**
	 * Banjul, Gambia
	 */
	@RosettaEnumValue(value = "GMBA") GMBA("GMBA"),
	
	/**
	 * Conakry, Guinea
	 */
	@RosettaEnumValue(value = "GNCO") GNCO("GNCO"),
	
	/**
	 * Athens, Greece
	 */
	@RosettaEnumValue(value = "GRAT") GRAT("GRAT"),
	
	/**
	 * Guatemala City, Guatemala
	 */
	@RosettaEnumValue(value = "GTGC") GTGC("GTGC"),
	
	/**
	 * Guatemala City, Guatemala [DEPRECATED, to be removed in 2024. Replaced by GTGC.]
	 */
	@RosettaEnumValue(value = "GUGC") GUGC("GUGC"),
	
	/**
	 * Hong Kong, Hong Kong
	 */
	@RosettaEnumValue(value = "HKHK") HKHK("HKHK"),
	
	/**
	 * Tegucigalpa, Honduras
	 */
	@RosettaEnumValue(value = "HNTE") HNTE("HNTE"),
	
	/**
	 * Zagreb, Republic of Croatia
	 */
	@RosettaEnumValue(value = "HRZA") HRZA("HRZA"),
	
	/**
	 * Budapest, Hungary
	 */
	@RosettaEnumValue(value = "HUBU") HUBU("HUBU"),
	
	/**
	 * Jakarta, Indonesia
	 */
	@RosettaEnumValue(value = "IDJA") IDJA("IDJA"),
	
	/**
	 * Dublin, Ireland
	 */
	@RosettaEnumValue(value = "IEDU") IEDU("IEDU"),
	
	/**
	 * Jerusalem, Israel
	 */
	@RosettaEnumValue(value = "ILJE") ILJE("ILJE"),
	
	/**
	 * Publication dates of the ILS-TELBOR index.
	 */
	@RosettaEnumValue(value = "ILS_TELBOR", displayName = "ILS-TELBOR") ILS_TELBOR("ILS_TELBOR", "ILS-TELBOR"),
	
	/**
	 * Tel Aviv, Israel
	 */
	@RosettaEnumValue(value = "ILTA") ILTA("ILTA"),
	
	/**
	 * Ahmedabad, India
	 */
	@RosettaEnumValue(value = "INAH") INAH("INAH"),
	
	/**
	 * Bangalore, India
	 */
	@RosettaEnumValue(value = "INBA") INBA("INBA"),
	
	/**
	 * Chennai, India
	 */
	@RosettaEnumValue(value = "INCH") INCH("INCH"),
	
	/**
	 * Hyderabad, India
	 */
	@RosettaEnumValue(value = "INHY") INHY("INHY"),
	
	/**
	 * Kolkata, India
	 */
	@RosettaEnumValue(value = "INKO") INKO("INKO"),
	
	/**
	 * Mumbai, India
	 */
	@RosettaEnumValue(value = "INMU") INMU("INMU"),
	
	/**
	 * New Delhi, India
	 */
	@RosettaEnumValue(value = "INND") INND("INND"),
	
	/**
	 * Baghdad, Iraq
	 */
	@RosettaEnumValue(value = "IQBA") IQBA("IQBA"),
	
	/**
	 * Teheran, Iran
	 */
	@RosettaEnumValue(value = "IRTE") IRTE("IRTE"),
	
	/**
	 * Reykjavik, Iceland
	 */
	@RosettaEnumValue(value = "ISRE") ISRE("ISRE"),
	
	/**
	 * Milan, Italy
	 */
	@RosettaEnumValue(value = "ITMI") ITMI("ITMI"),
	
	/**
	 * Rome, Italy
	 */
	@RosettaEnumValue(value = "ITRO") ITRO("ITRO"),
	
	/**
	 * Turin, Italy
	 */
	@RosettaEnumValue(value = "ITTU") ITTU("ITTU"),
	
	/**
	 * St. Helier, Channel Islands, Jersey
	 */
	@RosettaEnumValue(value = "JESH") JESH("JESH"),
	
	/**
	 * Kingston, Jamaica
	 */
	@RosettaEnumValue(value = "JMKI") JMKI("JMKI"),
	
	/**
	 * Amman, Jordan
	 */
	@RosettaEnumValue(value = "JOAM") JOAM("JOAM"),
	
	/**
	 * Tokyo, Japan
	 */
	@RosettaEnumValue(value = "JPTO") JPTO("JPTO"),
	
	/**
	 * Nairobi, Kenya
	 */
	@RosettaEnumValue(value = "KENA") KENA("KENA"),
	
	/**
	 * Phnom Penh, Cambodia
	 */
	@RosettaEnumValue(value = "KHPP") KHPP("KHPP"),
	
	/**
	 * Seoul, Republic of Korea
	 */
	@RosettaEnumValue(value = "KRSE") KRSE("KRSE"),
	
	/**
	 * Kuwait City, Kuwait
	 */
	@RosettaEnumValue(value = "KWKC") KWKC("KWKC"),
	
	/**
	 * George Town, Cayman Islands
	 */
	@RosettaEnumValue(value = "KYGE") KYGE("KYGE"),
	
	/**
	 * Almaty, Kazakhstan
	 */
	@RosettaEnumValue(value = "KZAL") KZAL("KZAL"),
	
	/**
	 * Vientiane, Laos
	 */
	@RosettaEnumValue(value = "LAVI") LAVI("LAVI"),
	
	/**
	 * Beirut, Lebanon
	 */
	@RosettaEnumValue(value = "LBBE") LBBE("LBBE"),
	
	/**
	 * Colombo, Sri Lanka
	 */
	@RosettaEnumValue(value = "LKCO") LKCO("LKCO"),
	
	/**
	 * Luxembourg, Luxembourg
	 */
	@RosettaEnumValue(value = "LULU") LULU("LULU"),
	
	/**
	 * Riga, Latvia
	 */
	@RosettaEnumValue(value = "LVRI") LVRI("LVRI"),
	
	/**
	 * Casablanca, Morocco
	 */
	@RosettaEnumValue(value = "MACA") MACA("MACA"),
	
	/**
	 * Rabat, Morocco
	 */
	@RosettaEnumValue(value = "MARA") MARA("MARA"),
	
	/**
	 * Monaco, Monaco
	 */
	@RosettaEnumValue(value = "MCMO") MCMO("MCMO"),
	
	/**
	 * Ulan Bator, Mongolia
	 */
	@RosettaEnumValue(value = "MNUB") MNUB("MNUB"),
	
	/**
	 * Macau, Macao
	 */
	@RosettaEnumValue(value = "MOMA") MOMA("MOMA"),
	
	/**
	 * Valletta, Malta
	 */
	@RosettaEnumValue(value = "MTVA") MTVA("MTVA"),
	
	/**
	 * Port Louis, Mauritius
	 */
	@RosettaEnumValue(value = "MUPL") MUPL("MUPL"),
	
	/**
	 * Male, Maldives
	 */
	@RosettaEnumValue(value = "MVMA") MVMA("MVMA"),
	
	/**
	 * Lilongwe, Malawi
	 */
	@RosettaEnumValue(value = "MWLI") MWLI("MWLI"),
	
	/**
	 * Mexico City, Mexico
	 */
	@RosettaEnumValue(value = "MXMC") MXMC("MXMC"),
	
	/**
	 * Kuala Lumpur, Malaysia
	 */
	@RosettaEnumValue(value = "MYKL") MYKL("MYKL"),
	
	/**
	 * Labuan, Malaysia
	 */
	@RosettaEnumValue(value = "MYLA") MYLA("MYLA"),
	
	/**
	 * Maputo, Mozambique
	 */
	@RosettaEnumValue(value = "MZMA") MZMA("MZMA"),
	
	/**
	 * Windhoek, Namibia
	 */
	@RosettaEnumValue(value = "NAWI") NAWI("NAWI"),
	
	/**
	 * Abuja, Nigeria
	 */
	@RosettaEnumValue(value = "NGAB") NGAB("NGAB"),
	
	/**
	 * Lagos, Nigeria
	 */
	@RosettaEnumValue(value = "NGLA") NGLA("NGLA"),
	
	/**
	 * Amsterdam, Netherlands
	 */
	@RosettaEnumValue(value = "NLAM") NLAM("NLAM"),
	
	/**
	 * Rotterdam, Netherlands
	 */
	@RosettaEnumValue(value = "NLRO") NLRO("NLRO"),
	
	/**
	 * Oslo, Norway
	 */
	@RosettaEnumValue(value = "NOOS") NOOS("NOOS"),
	
	/**
	 * Kathmandu, Nepal
	 */
	@RosettaEnumValue(value = "NPKA") NPKA("NPKA"),
	
	/**
	 * New York Fed Business Day (as defined in 2006 ISDA Definitions Section 1.9, 2000 ISDA Definitions Section 1.9, and 2021 ISDA Definitions Section 2.1.7)
	 */
	@RosettaEnumValue(value = "NYFD") NYFD("NYFD"),
	
	/**
	 * New York Stock Exchange Business Day (as defined in 2006 ISDA Definitions Section 1.10, 2000 ISDA Definitions Section 1.10, and 2021 ISDA Definitions Section 2.1.8)
	 */
	@RosettaEnumValue(value = "NYSE") NYSE("NYSE"),
	
	/**
	 * Auckland, New Zealand
	 */
	@RosettaEnumValue(value = "NZAU") NZAU("NZAU"),
	
	/**
	 * Wellington, New Zealand
	 */
	@RosettaEnumValue(value = "NZWE") NZWE("NZWE"),
	
	/**
	 * Muscat, Oman
	 */
	@RosettaEnumValue(value = "OMMU") OMMU("OMMU"),
	
	/**
	 * Panama City, Panama
	 */
	@RosettaEnumValue(value = "PAPC") PAPC("PAPC"),
	
	/**
	 * Lima, Peru
	 */
	@RosettaEnumValue(value = "PELI") PELI("PELI"),
	
	/**
	 * Manila, Philippines
	 */
	@RosettaEnumValue(value = "PHMA") PHMA("PHMA"),
	
	/**
	 * Makati, Philippines
	 */
	@RosettaEnumValue(value = "PHMK") PHMK("PHMK"),
	
	/**
	 * Karachi, Pakistan
	 */
	@RosettaEnumValue(value = "PKKA") PKKA("PKKA"),
	
	/**
	 * Warsaw, Poland
	 */
	@RosettaEnumValue(value = "PLWA") PLWA("PLWA"),
	
	/**
	 * San Juan, Puerto Rico
	 */
	@RosettaEnumValue(value = "PRSJ") PRSJ("PRSJ"),
	
	/**
	 * Lisbon, Portugal
	 */
	@RosettaEnumValue(value = "PTLI") PTLI("PTLI"),
	
	/**
	 * Doha, Qatar
	 */
	@RosettaEnumValue(value = "QADO") QADO("QADO"),
	
	/**
	 * Bucharest, Romania
	 */
	@RosettaEnumValue(value = "ROBU") ROBU("ROBU"),
	
	/**
	 * Belgrade, Serbia
	 */
	@RosettaEnumValue(value = "RSBE") RSBE("RSBE"),
	
	/**
	 * Moscow, Russian Federation
	 */
	@RosettaEnumValue(value = "RUMO") RUMO("RUMO"),
	
	/**
	 * Abha, Saudi Arabia
	 */
	@RosettaEnumValue(value = "SAAB") SAAB("SAAB"),
	
	/**
	 * Jeddah, Saudi Arabia
	 */
	@RosettaEnumValue(value = "SAJE") SAJE("SAJE"),
	
	/**
	 * Riyadh, Saudi Arabia
	 */
	@RosettaEnumValue(value = "SARI") SARI("SARI"),
	
	/**
	 * Stockholm, Sweden
	 */
	@RosettaEnumValue(value = "SEST") SEST("SEST"),
	
	/**
	 * Singapore, Singapore
	 */
	@RosettaEnumValue(value = "SGSI") SGSI("SGSI"),
	
	/**
	 * Ljubljana, Slovenia
	 */
	@RosettaEnumValue(value = "SILJ") SILJ("SILJ"),
	
	/**
	 * Bratislava, Slovakia
	 */
	@RosettaEnumValue(value = "SKBR") SKBR("SKBR"),
	
	/**
	 * Freetown, Sierra Leone
	 */
	@RosettaEnumValue(value = "SLFR") SLFR("SLFR"),
	
	/**
	 * Dakar, Senegal
	 */
	@RosettaEnumValue(value = "SNDA") SNDA("SNDA"),
	
	/**
	 * San Salvador, El Salvador
	 */
	@RosettaEnumValue(value = "SVSS") SVSS("SVSS"),
	
	/**
	 * Bangkok, Thailand
	 */
	@RosettaEnumValue(value = "THBA") THBA("THBA"),
	
	/**
	 * Tunis, Tunisia
	 */
	@RosettaEnumValue(value = "TNTU") TNTU("TNTU"),
	
	/**
	 * Ankara, Turkey
	 */
	@RosettaEnumValue(value = "TRAN") TRAN("TRAN"),
	
	/**
	 * Istanbul, Turkey
	 */
	@RosettaEnumValue(value = "TRIS") TRIS("TRIS"),
	
	/**
	 * Port of Spain, Trinidad and Tobago
	 */
	@RosettaEnumValue(value = "TTPS") TTPS("TTPS"),
	
	/**
	 * Taipei, Taiwan
	 */
	@RosettaEnumValue(value = "TWTA") TWTA("TWTA"),
	
	/**
	 * Dar es Salaam, Tanzania
	 */
	@RosettaEnumValue(value = "TZDA") TZDA("TZDA"),
	
	/**
	 * Dodoma, Tanzania
	 */
	@RosettaEnumValue(value = "TZDO") TZDO("TZDO"),
	
	/**
	 * Kiev, Ukraine
	 */
	@RosettaEnumValue(value = "UAKI") UAKI("UAKI"),
	
	/**
	 * Kampala, Uganda
	 */
	@RosettaEnumValue(value = "UGKA") UGKA("UGKA"),
	
	/**
	 * Boston, Massachusetts, United States
	 */
	@RosettaEnumValue(value = "USBO") USBO("USBO"),
	
	/**
	 * Chicago, United States
	 */
	@RosettaEnumValue(value = "USCH") USCH("USCH"),
	
	/**
	 * Charlotte, North Carolina, United States
	 */
	@RosettaEnumValue(value = "USCR") USCR("USCR"),
	
	/**
	 * Washington, District of Columbia, United States
	 */
	@RosettaEnumValue(value = "USDC") USDC("USDC"),
	
	/**
	 * Publication dates for ICE Swap rates based on USD-LIBOR rates
	 */
	@RosettaEnumValue(value = "USD_ICESWAP", displayName = "USD-ICESWAP") USD_ICESWAP("USD_ICESWAP", "USD-ICESWAP"),
	
	/**
	 * Publication dates for the USD-Municipal Swap Index
	 */
	@RosettaEnumValue(value = "USD_MUNI", displayName = "USD-MUNI") USD_MUNI("USD_MUNI", "USD-MUNI"),
	
	/**
	 * Denver, United States
	 */
	@RosettaEnumValue(value = "USDN") USDN("USDN"),
	
	/**
	 * Detroit, Michigan, United States
	 */
	@RosettaEnumValue(value = "USDT") USDT("USDT"),
	
	/**
	 * U.S. Government Securities Business Day (as defined in 2006 ISDA Definitions Section 1.11 and 2000 ISDA Definitions Section 1.11)
	 */
	@RosettaEnumValue(value = "USGS") USGS("USGS"),
	
	/**
	 * Honolulu, Hawaii, United States
	 */
	@RosettaEnumValue(value = "USHL") USHL("USHL"),
	
	/**
	 * Houston, United States
	 */
	@RosettaEnumValue(value = "USHO") USHO("USHO"),
	
	/**
	 * Los Angeles, United States
	 */
	@RosettaEnumValue(value = "USLA") USLA("USLA"),
	
	/**
	 * Mobile, Alabama, United States
	 */
	@RosettaEnumValue(value = "USMB") USMB("USMB"),
	
	/**
	 * Minneapolis, United States
	 */
	@RosettaEnumValue(value = "USMN") USMN("USMN"),
	
	/**
	 * New York, United States
	 */
	@RosettaEnumValue(value = "USNY") USNY("USNY"),
	
	/**
	 * Portland, Oregon, United States
	 */
	@RosettaEnumValue(value = "USPO") USPO("USPO"),
	
	/**
	 * Sacramento, California, United States
	 */
	@RosettaEnumValue(value = "USSA") USSA("USSA"),
	
	/**
	 * Seattle, United States
	 */
	@RosettaEnumValue(value = "USSE") USSE("USSE"),
	
	/**
	 * San Francisco, United States
	 */
	@RosettaEnumValue(value = "USSF") USSF("USSF"),
	
	/**
	 * Wichita, United States
	 */
	@RosettaEnumValue(value = "USWT") USWT("USWT"),
	
	/**
	 * Montevideo, Uruguay
	 */
	@RosettaEnumValue(value = "UYMO") UYMO("UYMO"),
	
	/**
	 * Tashkent, Uzbekistan
	 */
	@RosettaEnumValue(value = "UZTA") UZTA("UZTA"),
	
	/**
	 * Caracas, Venezuela
	 */
	@RosettaEnumValue(value = "VECA") VECA("VECA"),
	
	/**
	 * Road Town, Virgin Islands (British)
	 */
	@RosettaEnumValue(value = "VGRT") VGRT("VGRT"),
	
	/**
	 * Hanoi, Vietnam
	 */
	@RosettaEnumValue(value = "VNHA") VNHA("VNHA"),
	
	/**
	 * Ho Chi Minh (formerly Saigon), Vietnam
	 */
	@RosettaEnumValue(value = "VNHC") VNHC("VNHC"),
	
	/**
	 * Aden, Yemen
	 */
	@RosettaEnumValue(value = "YEAD") YEAD("YEAD"),
	
	/**
	 * Johannesburg, South Africa
	 */
	@RosettaEnumValue(value = "ZAJO") ZAJO("ZAJO"),
	
	/**
	 * Lusaka, Zambia
	 */
	@RosettaEnumValue(value = "ZMLU") ZMLU("ZMLU"),
	
	/**
	 * Harare, Zimbabwe
	 */
	@RosettaEnumValue(value = "ZWHA") ZWHA("ZWHA")
;
	private static Map<String, BusinessCenterEnum> values;
	static {
        Map<String, BusinessCenterEnum> map = new ConcurrentHashMap<>();
		for (BusinessCenterEnum instance : BusinessCenterEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	BusinessCenterEnum(String rosettaName) {
		this(rosettaName, null);
	}

	BusinessCenterEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static BusinessCenterEnum fromDisplayName(String name) {
		BusinessCenterEnum value = values.get(name);
		if (value == null) {
			throw new IllegalArgumentException("No enum constant with display name \"" + name + "\".");
		}
		return value;
	}

	@Override
	public String toString() {
		return toDisplayString();
	}

	public String toDisplayString() {
		return displayName != null ?  displayName : rosettaName;
	}
}
