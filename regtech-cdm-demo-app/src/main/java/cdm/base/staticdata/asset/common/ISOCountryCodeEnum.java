package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify standard currency codes according to the International Standards Organization (ISO). The set of codes in this enumerated list is sourced from ISO Standard 3166 (ISO-3166-1alpha-2)(https://www.iso.org/iso-3166-country-codes.html) as at 14-Aug-23.
 * @version ${project.version}
 */
@RosettaEnum("ISOCountryCodeEnum")
public enum ISOCountryCodeEnum {

	/**
	 * Afghanistan
	 */
	@RosettaEnumValue(value = "AF") AF("AF"),
	
	/**
	 * Albania
	 */
	@RosettaEnumValue(value = "AL") AL("AL"),
	
	/**
	 * Algeria
	 */
	@RosettaEnumValue(value = "DZ") DZ("DZ"),
	
	/**
	 * American Samoa
	 */
	@RosettaEnumValue(value = "AS") AS("AS"),
	
	/**
	 * Andorra
	 */
	@RosettaEnumValue(value = "AD") AD("AD"),
	
	/**
	 * Angola
	 */
	@RosettaEnumValue(value = "AO") AO("AO"),
	
	/**
	 * Anguilla
	 */
	@RosettaEnumValue(value = "AI") AI("AI"),
	
	/**
	 * Antarctica
	 */
	@RosettaEnumValue(value = "AQ") AQ("AQ"),
	
	/**
	 * Antigua and Barbuda
	 */
	@RosettaEnumValue(value = "AG") AG("AG"),
	
	/**
	 * Argentina
	 */
	@RosettaEnumValue(value = "AR") AR("AR"),
	
	/**
	 * Armenia
	 */
	@RosettaEnumValue(value = "AM") AM("AM"),
	
	/**
	 * Aruba
	 */
	@RosettaEnumValue(value = "AW") AW("AW"),
	
	/**
	 * Australia
	 */
	@RosettaEnumValue(value = "AU") AU("AU"),
	
	/**
	 * Austria
	 */
	@RosettaEnumValue(value = "AT") AT("AT"),
	
	/**
	 * Azerbaijan
	 */
	@RosettaEnumValue(value = "AZ") AZ("AZ"),
	
	/**
	 * Bahamas (the)
	 */
	@RosettaEnumValue(value = "BS") BS("BS"),
	
	/**
	 * Bahrain
	 */
	@RosettaEnumValue(value = "BH") BH("BH"),
	
	/**
	 * Bangladesh
	 */
	@RosettaEnumValue(value = "BD") BD("BD"),
	
	/**
	 * Barbados
	 */
	@RosettaEnumValue(value = "BB") BB("BB"),
	
	/**
	 * Belarus
	 */
	@RosettaEnumValue(value = "BY") BY("BY"),
	
	/**
	 * Belgium
	 */
	@RosettaEnumValue(value = "BE") BE("BE"),
	
	/**
	 * Belize
	 */
	@RosettaEnumValue(value = "BZ") BZ("BZ"),
	
	/**
	 * Benin
	 */
	@RosettaEnumValue(value = "BJ") BJ("BJ"),
	
	/**
	 * Bermuda
	 */
	@RosettaEnumValue(value = "BM") BM("BM"),
	
	/**
	 * Aland Islands
	 */
	@RosettaEnumValue(value = "AX") AX("AX"),
	
	/**
	 * Bhutan
	 */
	@RosettaEnumValue(value = "BT") BT("BT"),
	
	/**
	 * Bolivia (Plurinational State of)
	 */
	@RosettaEnumValue(value = "BO") BO("BO"),
	
	/**
	 * Bonaire, Sint Eustatius and Saba
	 */
	@RosettaEnumValue(value = "BQ") BQ("BQ"),
	
	/**
	 * Bosnia and Herzegovina
	 */
	@RosettaEnumValue(value = "BA") BA("BA"),
	
	/**
	 * Botswana
	 */
	@RosettaEnumValue(value = "BW") BW("BW"),
	
	/**
	 * Bouvet Island
	 */
	@RosettaEnumValue(value = "BV") BV("BV"),
	
	/**
	 * Brazil
	 */
	@RosettaEnumValue(value = "BR") BR("BR"),
	
	/**
	 * British Indian Ocean Territory (the)
	 */
	@RosettaEnumValue(value = "IO") IO("IO"),
	
	/**
	 * Brunei Darussalam
	 */
	@RosettaEnumValue(value = "BN") BN("BN"),
	
	/**
	 * Bulgaria
	 */
	@RosettaEnumValue(value = "BG") BG("BG"),
	
	/**
	 * Burkina Faso
	 */
	@RosettaEnumValue(value = "BF") BF("BF"),
	
	/**
	 * Burundi
	 */
	@RosettaEnumValue(value = "BI") BI("BI"),
	
	/**
	 * Cabo Verde
	 */
	@RosettaEnumValue(value = "CV") CV("CV"),
	
	/**
	 * Cambodia
	 */
	@RosettaEnumValue(value = "KH") KH("KH"),
	
	/**
	 * Cameroon
	 */
	@RosettaEnumValue(value = "CM") CM("CM"),
	
	/**
	 * Canada
	 */
	@RosettaEnumValue(value = "CA") CA("CA"),
	
	/**
	 * Cayman Islands (the)
	 */
	@RosettaEnumValue(value = "KY") KY("KY"),
	
	/**
	 * Central African Republic (the)
	 */
	@RosettaEnumValue(value = "CF") CF("CF"),
	
	/**
	 * Chad
	 */
	@RosettaEnumValue(value = "TD") TD("TD"),
	
	/**
	 * Chile
	 */
	@RosettaEnumValue(value = "CL") CL("CL"),
	
	/**
	 * China
	 */
	@RosettaEnumValue(value = "CN") CN("CN"),
	
	/**
	 * Christmas Island
	 */
	@RosettaEnumValue(value = "CX") CX("CX"),
	
	/**
	 * Cocos (Keeling) Islands (the)
	 */
	@RosettaEnumValue(value = "CC") CC("CC"),
	
	/**
	 * Colombia
	 */
	@RosettaEnumValue(value = "CO") CO("CO"),
	
	/**
	 * Comoros (the)
	 */
	@RosettaEnumValue(value = "KM") KM("KM"),
	
	/**
	 * Congo (the Democratic Republic of the)
	 */
	@RosettaEnumValue(value = "CD") CD("CD"),
	
	/**
	 * Congo (the)
	 */
	@RosettaEnumValue(value = "CG") CG("CG"),
	
	/**
	 * Cook Islands (the)
	 */
	@RosettaEnumValue(value = "CK") CK("CK"),
	
	/**
	 * Costa Rica
	 */
	@RosettaEnumValue(value = "CR") CR("CR"),
	
	/**
	 * Croatia
	 */
	@RosettaEnumValue(value = "HR") HR("HR"),
	
	/**
	 * Cuba
	 */
	@RosettaEnumValue(value = "CU") CU("CU"),
	
	/**
	 * Curaao
	 */
	@RosettaEnumValue(value = "CW") CW("CW"),
	
	/**
	 * Cyprus
	 */
	@RosettaEnumValue(value = "CY") CY("CY"),
	
	/**
	 * Czechia
	 */
	@RosettaEnumValue(value = "CZ") CZ("CZ"),
	
	/**
	 * Cte d&#39;Ivoire
	 */
	@RosettaEnumValue(value = "CI") CI("CI"),
	
	/**
	 * Denmark
	 */
	@RosettaEnumValue(value = "DK") DK("DK"),
	
	/**
	 * Djibouti
	 */
	@RosettaEnumValue(value = "DJ") DJ("DJ"),
	
	/**
	 * Dominica
	 */
	@RosettaEnumValue(value = "DM") DM("DM"),
	
	/**
	 * Dominican Republic (the)
	 */
	@RosettaEnumValue(value = "DO") DO("DO"),
	
	/**
	 * Ecuador
	 */
	@RosettaEnumValue(value = "EC") EC("EC"),
	
	/**
	 * Egypt
	 */
	@RosettaEnumValue(value = "EG") EG("EG"),
	
	/**
	 * El Salvador
	 */
	@RosettaEnumValue(value = "SV") SV("SV"),
	
	/**
	 * Equatorial Guinea
	 */
	@RosettaEnumValue(value = "GQ") GQ("GQ"),
	
	/**
	 * Eritrea
	 */
	@RosettaEnumValue(value = "ER") ER("ER"),
	
	/**
	 * Estonia
	 */
	@RosettaEnumValue(value = "EE") EE("EE"),
	
	/**
	 * Eswatini
	 */
	@RosettaEnumValue(value = "SZ") SZ("SZ"),
	
	/**
	 * Ethiopia
	 */
	@RosettaEnumValue(value = "ET") ET("ET"),
	
	/**
	 * Falkland Islands (the) [Malvinas]
	 */
	@RosettaEnumValue(value = "FK") FK("FK"),
	
	/**
	 * Faroe Islands (the)
	 */
	@RosettaEnumValue(value = "FO") FO("FO"),
	
	/**
	 * Fiji
	 */
	@RosettaEnumValue(value = "FJ") FJ("FJ"),
	
	/**
	 * Finland
	 */
	@RosettaEnumValue(value = "FI") FI("FI"),
	
	/**
	 * France
	 */
	@RosettaEnumValue(value = "FR") FR("FR"),
	
	/**
	 * French Guiana
	 */
	@RosettaEnumValue(value = "GF") GF("GF"),
	
	/**
	 * French Polynesia
	 */
	@RosettaEnumValue(value = "PF") PF("PF"),
	
	/**
	 * French Southern Territories (the)
	 */
	@RosettaEnumValue(value = "TF") TF("TF"),
	
	/**
	 * Gabon
	 */
	@RosettaEnumValue(value = "GA") GA("GA"),
	
	/**
	 * Gambia (the)
	 */
	@RosettaEnumValue(value = "GM") GM("GM"),
	
	/**
	 * Georgia
	 */
	@RosettaEnumValue(value = "GE") GE("GE"),
	
	/**
	 * Germany
	 */
	@RosettaEnumValue(value = "DE") DE("DE"),
	
	/**
	 * Ghana
	 */
	@RosettaEnumValue(value = "GH") GH("GH"),
	
	/**
	 * Gibraltar
	 */
	@RosettaEnumValue(value = "GI") GI("GI"),
	
	/**
	 * Greece
	 */
	@RosettaEnumValue(value = "GR") GR("GR"),
	
	/**
	 * Greenland
	 */
	@RosettaEnumValue(value = "GL") GL("GL"),
	
	/**
	 * Grenada
	 */
	@RosettaEnumValue(value = "GD") GD("GD"),
	
	/**
	 * Guadeloupe
	 */
	@RosettaEnumValue(value = "GP") GP("GP"),
	
	/**
	 * Guam
	 */
	@RosettaEnumValue(value = "GU") GU("GU"),
	
	/**
	 * Guatemala
	 */
	@RosettaEnumValue(value = "GT") GT("GT"),
	
	/**
	 * Guernsey
	 */
	@RosettaEnumValue(value = "GG") GG("GG"),
	
	/**
	 * Guinea
	 */
	@RosettaEnumValue(value = "GN") GN("GN"),
	
	/**
	 * Guinea-Bissau
	 */
	@RosettaEnumValue(value = "GW") GW("GW"),
	
	/**
	 * Guyana
	 */
	@RosettaEnumValue(value = "GY") GY("GY"),
	
	/**
	 * Haiti
	 */
	@RosettaEnumValue(value = "HT") HT("HT"),
	
	/**
	 * Heard Island and McDonald Islands
	 */
	@RosettaEnumValue(value = "HM") HM("HM"),
	
	/**
	 * Holy See (the)
	 */
	@RosettaEnumValue(value = "VA") VA("VA"),
	
	/**
	 * Honduras
	 */
	@RosettaEnumValue(value = "HN") HN("HN"),
	
	/**
	 * Hong Kong
	 */
	@RosettaEnumValue(value = "HK") HK("HK"),
	
	/**
	 * Hungary
	 */
	@RosettaEnumValue(value = "HU") HU("HU"),
	
	/**
	 * Iceland
	 */
	@RosettaEnumValue(value = "IS") IS("IS"),
	
	/**
	 * India
	 */
	@RosettaEnumValue(value = "IN") IN("IN"),
	
	/**
	 * Indonesia
	 */
	@RosettaEnumValue(value = "ID") ID("ID"),
	
	/**
	 * Iran (Islamic Republic of)
	 */
	@RosettaEnumValue(value = "IR") IR("IR"),
	
	/**
	 * Iraq
	 */
	@RosettaEnumValue(value = "IQ") IQ("IQ"),
	
	/**
	 * Ireland
	 */
	@RosettaEnumValue(value = "IE") IE("IE"),
	
	/**
	 * Isle of Man
	 */
	@RosettaEnumValue(value = "IM") IM("IM"),
	
	/**
	 * Israel
	 */
	@RosettaEnumValue(value = "IL") IL("IL"),
	
	/**
	 * Italy
	 */
	@RosettaEnumValue(value = "IT") IT("IT"),
	
	/**
	 * Jamaica
	 */
	@RosettaEnumValue(value = "JM") JM("JM"),
	
	/**
	 * Japan
	 */
	@RosettaEnumValue(value = "JP") JP("JP"),
	
	/**
	 * Jersey
	 */
	@RosettaEnumValue(value = "JE") JE("JE"),
	
	/**
	 * Jordan
	 */
	@RosettaEnumValue(value = "JO") JO("JO"),
	
	/**
	 * Kazakhstan
	 */
	@RosettaEnumValue(value = "KZ") KZ("KZ"),
	
	/**
	 * Kenya
	 */
	@RosettaEnumValue(value = "KE") KE("KE"),
	
	/**
	 * Kiribati
	 */
	@RosettaEnumValue(value = "KI") KI("KI"),
	
	/**
	 * Korea (the Democratic People&#39;s Republic of)
	 */
	@RosettaEnumValue(value = "KP") KP("KP"),
	
	/**
	 * Korea (the Republic of)
	 */
	@RosettaEnumValue(value = "KR") KR("KR"),
	
	/**
	 * Kuwait
	 */
	@RosettaEnumValue(value = "KW") KW("KW"),
	
	/**
	 * Kyrgyzstan
	 */
	@RosettaEnumValue(value = "KG") KG("KG"),
	
	/**
	 * Lao People&#39;s Democratic Republic (the)
	 */
	@RosettaEnumValue(value = "LA") LA("LA"),
	
	/**
	 * Latvia
	 */
	@RosettaEnumValue(value = "LV") LV("LV"),
	
	/**
	 * Lebanon
	 */
	@RosettaEnumValue(value = "LB") LB("LB"),
	
	/**
	 * Lesotho
	 */
	@RosettaEnumValue(value = "LS") LS("LS"),
	
	/**
	 * Liberia
	 */
	@RosettaEnumValue(value = "LR") LR("LR"),
	
	/**
	 * Libya
	 */
	@RosettaEnumValue(value = "LY") LY("LY"),
	
	/**
	 * Liechtenstein
	 */
	@RosettaEnumValue(value = "LI") LI("LI"),
	
	/**
	 * Lithuania
	 */
	@RosettaEnumValue(value = "LT") LT("LT"),
	
	/**
	 * Luxembourg
	 */
	@RosettaEnumValue(value = "LU") LU("LU"),
	
	/**
	 * Macao
	 */
	@RosettaEnumValue(value = "MO") MO("MO"),
	
	/**
	 * Madagascar
	 */
	@RosettaEnumValue(value = "MG") MG("MG"),
	
	/**
	 * Malawi
	 */
	@RosettaEnumValue(value = "MW") MW("MW"),
	
	/**
	 * Malaysia
	 */
	@RosettaEnumValue(value = "MY") MY("MY"),
	
	/**
	 * Maldives
	 */
	@RosettaEnumValue(value = "MV") MV("MV"),
	
	/**
	 * Mali
	 */
	@RosettaEnumValue(value = "ML") ML("ML"),
	
	/**
	 * Malta
	 */
	@RosettaEnumValue(value = "MT") MT("MT"),
	
	/**
	 * Marshall Islands (the)
	 */
	@RosettaEnumValue(value = "MH") MH("MH"),
	
	/**
	 * Martinique
	 */
	@RosettaEnumValue(value = "MQ") MQ("MQ"),
	
	/**
	 * Mauritania
	 */
	@RosettaEnumValue(value = "MR") MR("MR"),
	
	/**
	 * Mauritius
	 */
	@RosettaEnumValue(value = "MU") MU("MU"),
	
	/**
	 * Mayotte
	 */
	@RosettaEnumValue(value = "YT") YT("YT"),
	
	/**
	 * Mexico
	 */
	@RosettaEnumValue(value = "MX") MX("MX"),
	
	/**
	 * Micronesia (Federated States of)
	 */
	@RosettaEnumValue(value = "FM") FM("FM"),
	
	/**
	 * Moldova (the Republic of)
	 */
	@RosettaEnumValue(value = "MD") MD("MD"),
	
	/**
	 * Monaco
	 */
	@RosettaEnumValue(value = "MC") MC("MC"),
	
	/**
	 * Mongolia
	 */
	@RosettaEnumValue(value = "MN") MN("MN"),
	
	/**
	 * Montenegro
	 */
	@RosettaEnumValue(value = "ME") ME("ME"),
	
	/**
	 * Montserrat
	 */
	@RosettaEnumValue(value = "MS") MS("MS"),
	
	/**
	 * Morocco
	 */
	@RosettaEnumValue(value = "MA") MA("MA"),
	
	/**
	 * Mozambique
	 */
	@RosettaEnumValue(value = "MZ") MZ("MZ"),
	
	/**
	 * Myanmar
	 */
	@RosettaEnumValue(value = "MM") MM("MM"),
	
	/**
	 * Namibia
	 */
	@RosettaEnumValue(value = "NA") NA("NA"),
	
	/**
	 * Nauru
	 */
	@RosettaEnumValue(value = "NR") NR("NR"),
	
	/**
	 * Nepal
	 */
	@RosettaEnumValue(value = "NP") NP("NP"),
	
	/**
	 * Netherlands (Kingdom of the)
	 */
	@RosettaEnumValue(value = "NL") NL("NL"),
	
	/**
	 * New Caledonia
	 */
	@RosettaEnumValue(value = "NC") NC("NC"),
	
	/**
	 * New Zealand
	 */
	@RosettaEnumValue(value = "NZ") NZ("NZ"),
	
	/**
	 * Nicaragua
	 */
	@RosettaEnumValue(value = "NI") NI("NI"),
	
	/**
	 * Niger (the)
	 */
	@RosettaEnumValue(value = "NE") NE("NE"),
	
	/**
	 * Nigeria
	 */
	@RosettaEnumValue(value = "NG") NG("NG"),
	
	/**
	 * Niue
	 */
	@RosettaEnumValue(value = "NU") NU("NU"),
	
	/**
	 * Norfolk Island
	 */
	@RosettaEnumValue(value = "NF") NF("NF"),
	
	/**
	 * North Macedonia
	 */
	@RosettaEnumValue(value = "MK") MK("MK"),
	
	/**
	 * Northern Mariana Islands (the)
	 */
	@RosettaEnumValue(value = "MP") MP("MP"),
	
	/**
	 * Norway
	 */
	@RosettaEnumValue(value = "NO") NO("NO"),
	
	/**
	 * Oman
	 */
	@RosettaEnumValue(value = "OM") OM("OM"),
	
	/**
	 * Pakistan
	 */
	@RosettaEnumValue(value = "PK") PK("PK"),
	
	/**
	 * Palau
	 */
	@RosettaEnumValue(value = "PW") PW("PW"),
	
	/**
	 * Palestine, State of
	 */
	@RosettaEnumValue(value = "PS") PS("PS"),
	
	/**
	 * Panama
	 */
	@RosettaEnumValue(value = "PA") PA("PA"),
	
	/**
	 * Papua New Guinea
	 */
	@RosettaEnumValue(value = "PG") PG("PG"),
	
	/**
	 * Paraguay
	 */
	@RosettaEnumValue(value = "PY") PY("PY"),
	
	/**
	 * Peru
	 */
	@RosettaEnumValue(value = "PE") PE("PE"),
	
	/**
	 * Philippines (the)
	 */
	@RosettaEnumValue(value = "PH") PH("PH"),
	
	/**
	 * Pitcairn
	 */
	@RosettaEnumValue(value = "PN") PN("PN"),
	
	/**
	 * Poland
	 */
	@RosettaEnumValue(value = "PL") PL("PL"),
	
	/**
	 * Portugal
	 */
	@RosettaEnumValue(value = "PT") PT("PT"),
	
	/**
	 * Puerto Rico
	 */
	@RosettaEnumValue(value = "PR") PR("PR"),
	
	/**
	 * Qatar
	 */
	@RosettaEnumValue(value = "QA") QA("QA"),
	
	/**
	 * Romania
	 */
	@RosettaEnumValue(value = "RO") RO("RO"),
	
	/**
	 * Russian Federation (the)
	 */
	@RosettaEnumValue(value = "RU") RU("RU"),
	
	/**
	 * Rwanda
	 */
	@RosettaEnumValue(value = "RW") RW("RW"),
	
	/**
	 * Runion
	 */
	@RosettaEnumValue(value = "RE") RE("RE"),
	
	/**
	 * Saint Barthlemy
	 */
	@RosettaEnumValue(value = "BL") BL("BL"),
	
	/**
	 * Saint Helena, Ascension and Tristan da Cunha
	 */
	@RosettaEnumValue(value = "SH") SH("SH"),
	
	/**
	 * Saint Kitts and Nevis
	 */
	@RosettaEnumValue(value = "KN") KN("KN"),
	
	/**
	 * Saint Lucia
	 */
	@RosettaEnumValue(value = "LC") LC("LC"),
	
	/**
	 * Saint Martin (French part)
	 */
	@RosettaEnumValue(value = "MF") MF("MF"),
	
	/**
	 * Saint Pierre and Miquelon
	 */
	@RosettaEnumValue(value = "PM") PM("PM"),
	
	/**
	 * Saint Vincent and the Grenadines
	 */
	@RosettaEnumValue(value = "VC") VC("VC"),
	
	/**
	 * Samoa
	 */
	@RosettaEnumValue(value = "WS") WS("WS"),
	
	/**
	 * San Marino
	 */
	@RosettaEnumValue(value = "SM") SM("SM"),
	
	/**
	 * Sao Tome and Principe
	 */
	@RosettaEnumValue(value = "ST") ST("ST"),
	
	/**
	 * Saudi Arabia
	 */
	@RosettaEnumValue(value = "SA") SA("SA"),
	
	/**
	 * Senegal
	 */
	@RosettaEnumValue(value = "SN") SN("SN"),
	
	/**
	 * Serbia
	 */
	@RosettaEnumValue(value = "RS") RS("RS"),
	
	/**
	 * Seychelles
	 */
	@RosettaEnumValue(value = "SC") SC("SC"),
	
	/**
	 * Sierra Leone
	 */
	@RosettaEnumValue(value = "SL") SL("SL"),
	
	/**
	 * Singapore
	 */
	@RosettaEnumValue(value = "SG") SG("SG"),
	
	/**
	 * Sint Maarten (Dutch part)
	 */
	@RosettaEnumValue(value = "SX") SX("SX"),
	
	/**
	 * Slovakia
	 */
	@RosettaEnumValue(value = "SK") SK("SK"),
	
	/**
	 * Slovenia
	 */
	@RosettaEnumValue(value = "SI") SI("SI"),
	
	/**
	 * Solomon Islands
	 */
	@RosettaEnumValue(value = "SB") SB("SB"),
	
	/**
	 * Somalia
	 */
	@RosettaEnumValue(value = "SO") SO("SO"),
	
	/**
	 * South Africa
	 */
	@RosettaEnumValue(value = "ZA") ZA("ZA"),
	
	/**
	 * South Georgia and the South Sandwich Islands
	 */
	@RosettaEnumValue(value = "GS") GS("GS"),
	
	/**
	 * South Sudan
	 */
	@RosettaEnumValue(value = "SS") SS("SS"),
	
	/**
	 * Spain
	 */
	@RosettaEnumValue(value = "ES") ES("ES"),
	
	/**
	 * Sri Lanka
	 */
	@RosettaEnumValue(value = "LK") LK("LK"),
	
	/**
	 * Sudan (the)
	 */
	@RosettaEnumValue(value = "SD") SD("SD"),
	
	/**
	 * Suriname
	 */
	@RosettaEnumValue(value = "SR") SR("SR"),
	
	/**
	 * Svalbard and Jan Mayen
	 */
	@RosettaEnumValue(value = "SJ") SJ("SJ"),
	
	/**
	 * Sweden
	 */
	@RosettaEnumValue(value = "SE") SE("SE"),
	
	/**
	 * Switzerland
	 */
	@RosettaEnumValue(value = "CH") CH("CH"),
	
	/**
	 * Syrian Arab Republic (the)
	 */
	@RosettaEnumValue(value = "SY") SY("SY"),
	
	/**
	 * Taiwan (Province of China)
	 */
	@RosettaEnumValue(value = "TW") TW("TW"),
	
	/**
	 * Tajikistan
	 */
	@RosettaEnumValue(value = "TJ") TJ("TJ"),
	
	/**
	 * Tanzania, the United Republic of
	 */
	@RosettaEnumValue(value = "TZ") TZ("TZ"),
	
	/**
	 * Thailand
	 */
	@RosettaEnumValue(value = "TH") TH("TH"),
	
	/**
	 * Timor-Leste
	 */
	@RosettaEnumValue(value = "TL") TL("TL"),
	
	/**
	 * Togo
	 */
	@RosettaEnumValue(value = "TG") TG("TG"),
	
	/**
	 * Tokelau
	 */
	@RosettaEnumValue(value = "TK") TK("TK"),
	
	/**
	 * Tonga
	 */
	@RosettaEnumValue(value = "TO") TO("TO"),
	
	/**
	 * Trinidad and Tobago
	 */
	@RosettaEnumValue(value = "TT") TT("TT"),
	
	/**
	 * Tunisia
	 */
	@RosettaEnumValue(value = "TN") TN("TN"),
	
	/**
	 * Turkmenistan
	 */
	@RosettaEnumValue(value = "TM") TM("TM"),
	
	/**
	 * Turks and Caicos Islands (the)
	 */
	@RosettaEnumValue(value = "TC") TC("TC"),
	
	/**
	 * Tuvalu
	 */
	@RosettaEnumValue(value = "TV") TV("TV"),
	
	/**
	 * Trkiye
	 */
	@RosettaEnumValue(value = "TR") TR("TR"),
	
	/**
	 * Uganda
	 */
	@RosettaEnumValue(value = "UG") UG("UG"),
	
	/**
	 * Ukraine
	 */
	@RosettaEnumValue(value = "UA") UA("UA"),
	
	/**
	 * United Arab Emirates (the)
	 */
	@RosettaEnumValue(value = "AE") AE("AE"),
	
	/**
	 * United Kingdom of Great Britain and Northern Ireland (the)
	 */
	@RosettaEnumValue(value = "GB") GB("GB"),
	
	/**
	 * United States Minor Outlying Islands (the)
	 */
	@RosettaEnumValue(value = "UM") UM("UM"),
	
	/**
	 * United States of America (the)
	 */
	@RosettaEnumValue(value = "US") US("US"),
	
	/**
	 * Uruguay
	 */
	@RosettaEnumValue(value = "UY") UY("UY"),
	
	/**
	 * Uzbekistan
	 */
	@RosettaEnumValue(value = "UZ") UZ("UZ"),
	
	/**
	 * Vanuatu
	 */
	@RosettaEnumValue(value = "VU") VU("VU"),
	
	/**
	 * Venezuela (Bolivarian Republic of)
	 */
	@RosettaEnumValue(value = "VE") VE("VE"),
	
	/**
	 * Viet Nam
	 */
	@RosettaEnumValue(value = "VN") VN("VN"),
	
	/**
	 * Virgin Islands (British)
	 */
	@RosettaEnumValue(value = "VG") VG("VG"),
	
	/**
	 * Virgin Islands (U.S.)
	 */
	@RosettaEnumValue(value = "VI") VI("VI"),
	
	/**
	 * Wallis and Futuna
	 */
	@RosettaEnumValue(value = "WF") WF("WF"),
	
	/**
	 * Western Sahara*
	 */
	@RosettaEnumValue(value = "EH") EH("EH"),
	
	/**
	 * Yemen
	 */
	@RosettaEnumValue(value = "YE") YE("YE"),
	
	/**
	 * Zambia
	 */
	@RosettaEnumValue(value = "ZM") ZM("ZM"),
	
	/**
	 * Zimbabwe
	 */
	@RosettaEnumValue(value = "ZW") ZW("ZW")
;
	private static Map<String, ISOCountryCodeEnum> values;
	static {
        Map<String, ISOCountryCodeEnum> map = new ConcurrentHashMap<>();
		for (ISOCountryCodeEnum instance : ISOCountryCodeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ISOCountryCodeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ISOCountryCodeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ISOCountryCodeEnum fromDisplayName(String name) {
		ISOCountryCodeEnum value = values.get(name);
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
