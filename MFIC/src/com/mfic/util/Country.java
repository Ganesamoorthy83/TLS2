package com.mfic.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Country {

	private static final LinkedHashMap<String, String> COUNTRIES = new LinkedHashMap<String, String>();
	static{
		COUNTRIES.put("USA", "United States");
		COUNTRIES.put("GBR", "United Kingdom");
		COUNTRIES.put("AFG", "Afghanistan");
		COUNTRIES.put("ALA", "Aland Islands");
		COUNTRIES.put("ALB", "Albania");
		COUNTRIES.put("DZA", "Algeria");
		COUNTRIES.put("ASM", "American Samoa");
		COUNTRIES.put("AND", "Andorra");
		COUNTRIES.put("AGO", "Angola");
		COUNTRIES.put("AIA", "Anguilla");
		COUNTRIES.put("ATA", "Antarctica");
		COUNTRIES.put("ATG", "Antigua And Barbuda");
		COUNTRIES.put("ARG", "Argentina");
		COUNTRIES.put("ARM", "Armenia");
		COUNTRIES.put("ABW", "Aruba");
		COUNTRIES.put("AUS", "Australia");
		COUNTRIES.put("AUT", "Austria");
		COUNTRIES.put("AZE", "Azerbaijan");
		COUNTRIES.put("BHS", "Bahamas");
		COUNTRIES.put("BHR", "Bahrain");
		COUNTRIES.put("BGD", "Bangladesh");
		COUNTRIES.put("BRB", "Barbados");
		COUNTRIES.put("BLR", "Belarus");
		COUNTRIES.put("BEL", "Belgium");
		COUNTRIES.put("BLZ", "Belize");
		COUNTRIES.put("BEN", "Benin");
		COUNTRIES.put("BMU", "Bermuda");
		COUNTRIES.put("BTN", "Bhutan");
		COUNTRIES.put("BOL", "Bolivia");
		COUNTRIES.put("BIH", "Bosnia And Herzegovina");
		COUNTRIES.put("BWA", "Botswana");
		COUNTRIES.put("BVT", "Bouvet Island");
		COUNTRIES.put("BRA", "Brazil");
		COUNTRIES.put("IOT", "British Indian Ocean Territory");
		COUNTRIES.put("BRN", "Brunei Darussalam");
		COUNTRIES.put("BGR", "Bulgaria");
		COUNTRIES.put("BFA", "Burkina Faso");
		COUNTRIES.put("BDI", "Burundi");
		COUNTRIES.put("KHM", "Cambodia");
		COUNTRIES.put("CMR", "Cameroon");
		COUNTRIES.put("CAN", "Canada");
		COUNTRIES.put("CPV", "Cape Verde");
		COUNTRIES.put("CYM", "Cayman Islands");
		COUNTRIES.put("CAF", "Central African Republic");
		COUNTRIES.put("TCD", "Chad");
		COUNTRIES.put("CHL", "Chile");
		COUNTRIES.put("CHN", "China");
		COUNTRIES.put("CXR", "Christmas Island");
		COUNTRIES.put("CCK", "Cocos (Keeling) Islands");
		COUNTRIES.put("COL", "Colombia");
		COUNTRIES.put("COM", "Comoros");
		COUNTRIES.put("COG", "Congo");
		COUNTRIES.put("COD", "Congo,The Democratic Republic Of The");
		COUNTRIES.put("COK", "Cook Islands");
		COUNTRIES.put("CRI", "Costa Rica");
		COUNTRIES.put("CIV", "Cote D\\Ivoire");
		COUNTRIES.put("HRV", "Croatia");
		COUNTRIES.put("CUB", "Cuba");
		COUNTRIES.put("CYP", "Cyprus");
		COUNTRIES.put("CZE", "Czech Republic");
		COUNTRIES.put("DNK", "Denmark");
		COUNTRIES.put("DJI", "Djibouti");
		COUNTRIES.put("DMA", "Dominica");
		COUNTRIES.put("DOM", "Dominican Republic");
		COUNTRIES.put("ECU", "Ecuador");
		COUNTRIES.put("EGY", "Egypt");
		COUNTRIES.put("SLV", "El Salvador");
		COUNTRIES.put("GNQ", "Equatorial Guinea");
		COUNTRIES.put("ERI", "Eritrea");
		COUNTRIES.put("EST", "Estonia");
		COUNTRIES.put("ETH", "Ethiopia");
		COUNTRIES.put("FLK", "Falkland Islands (Malvinas)");
		COUNTRIES.put("FRO", "Faroe Islands");
		COUNTRIES.put("FJI", "Fiji");
		COUNTRIES.put("FIN", "Finland");
		COUNTRIES.put("FRA", "France");
		COUNTRIES.put("GUF", "French Guiana");
		COUNTRIES.put("PYF", "French Polynesia");
		COUNTRIES.put("ATF", "French Southern Territories");
		COUNTRIES.put("GAB", "Gabon");
		COUNTRIES.put("GMB", "Gambia");
		COUNTRIES.put("GEO", "Georgia");
		COUNTRIES.put("DEU", "Germany");
		COUNTRIES.put("GHA", "Ghana");
		COUNTRIES.put("GIB", "Gibraltar");
		COUNTRIES.put("GRC", "Greece");
		COUNTRIES.put("GRL", "Greenland");
		COUNTRIES.put("GRD", "Grenada");
		COUNTRIES.put("GLP", "Guadeloupe");
		COUNTRIES.put("GUM", "Guam");
		COUNTRIES.put("GTM", "Guatemala");
		COUNTRIES.put("GGY", "Guernsey");
		COUNTRIES.put("GIN", "Guinea");
		COUNTRIES.put("GNB", "Guinea-Bissau");
		COUNTRIES.put("GUY", "Guyana");
		COUNTRIES.put("HTI", "Haiti");
		COUNTRIES.put("HMD", "Heard Island And Mcdonald Islands");
		COUNTRIES.put("VAT", "Holy See (Vatican City State)");
		COUNTRIES.put("HND", "Honduras");
		COUNTRIES.put("HKG", "Hong Kong");
		COUNTRIES.put("HUN", "Hungary");
		COUNTRIES.put("ISL", "Iceland");
		COUNTRIES.put("IND", "India");
		COUNTRIES.put("IDN", "Indonesia");
		COUNTRIES.put("IRN", "Iran, Islamic Republic Of");
		COUNTRIES.put("IRQ", "Iraq");
		COUNTRIES.put("IRL", "Ireland");
		COUNTRIES.put("IMM", "Isle Of Man");
		COUNTRIES.put("ISR", "Israel");
		COUNTRIES.put("ITA", "Italy");
		COUNTRIES.put("JAM", "Jamaica");
		COUNTRIES.put("JPN", "Japan");
		COUNTRIES.put("JEY", "Jersey");
		COUNTRIES.put("JOR", "Jordan");
		COUNTRIES.put("KAZ", "Kazakhstan");
		COUNTRIES.put("KEN", "Kenya");
		COUNTRIES.put("KIR", "Kiribati");
		COUNTRIES.put("PRK", "Korea, Democratic People\\S Republic Of");
		COUNTRIES.put("KOR", "Korea, Republic Of");
		COUNTRIES.put("KWT", "Kuwait");
		COUNTRIES.put("KGZ", "Kyrgyzstan");
		COUNTRIES.put("LAO", "Lao People\\S Democratic Republic");
		COUNTRIES.put("LVA", "Latvia");
		COUNTRIES.put("LBN", "Lebanon");
		COUNTRIES.put("LSO", "Lesotho");
		COUNTRIES.put("LBR", "Liberia");
		COUNTRIES.put("LBY", "Libyan Arab Jamahiriya");
		COUNTRIES.put("LIE", "Liechtenstein");
		COUNTRIES.put("LTU", "Lithuania");
		COUNTRIES.put("LUX", "Luxembourg");
		COUNTRIES.put("MAC", "Macao");
		COUNTRIES.put("MKD", "Macedonia, The Former Yugoslav Republic Of");
		COUNTRIES.put("MDG", "Madagascar");
		COUNTRIES.put("MWI", "Malawi");
		COUNTRIES.put("MYS", "Malaysia");
		COUNTRIES.put("MDV", "Maldives");
		COUNTRIES.put("MLI", "Mali");
		COUNTRIES.put("MLT", "Malta");
		COUNTRIES.put("MHL", "Marshall Islands");
		COUNTRIES.put("MTQ", "Martinique");
		COUNTRIES.put("MRT", "Mauritania");
		COUNTRIES.put("MUS", "Mauritius");
		COUNTRIES.put("MYT", "Mayotte");
		COUNTRIES.put("MEX", "Mexico");
		COUNTRIES.put("FSM", "Micronesia, Federated States Of");
		COUNTRIES.put("MDA", "Moldova");
		COUNTRIES.put("MCO", "Monaco");
		COUNTRIES.put("MNG", "Mongolia");
		COUNTRIES.put("MNE", "Montenegro");
		COUNTRIES.put("MSR", "Montserrat");
		COUNTRIES.put("MAR", "Morocco");
		COUNTRIES.put("MOZ", "Mozambique");
		COUNTRIES.put("MMR", "Myanmar");
		COUNTRIES.put("NAM", "Namibia");
		COUNTRIES.put("NRU", "Nauru");
		COUNTRIES.put("NPL", "Nepal");
		COUNTRIES.put("NLD", "Netherlands");
		COUNTRIES.put("ANT", "Netherlands Antilles");
		COUNTRIES.put("NCL", "New Caledonia");
		COUNTRIES.put("NZL", "New Zealand");
		COUNTRIES.put("NIC", "Nicaragua");
		COUNTRIES.put("NER", "Niger");
		COUNTRIES.put("NGA", "Nigeria");
		COUNTRIES.put("NIU", "Niue");
		COUNTRIES.put("NFK", "Norfolk Island");
		COUNTRIES.put("MNP", "Northern Mariana Islands");
		COUNTRIES.put("NOR", "Norway");
		COUNTRIES.put("OMN", "Oman");
		COUNTRIES.put("PAK", "Pakistan");
		COUNTRIES.put("PLW", "Palau");
		COUNTRIES.put("PSE", "Palestinian Territory, Occupied");
		COUNTRIES.put("PAN", "Panama");
		COUNTRIES.put("PNG", "Papua New Guinea");
		COUNTRIES.put("PRY", "Paraguay");
		COUNTRIES.put("PER", "Peru");
		COUNTRIES.put("PHL", "Philippines");
		COUNTRIES.put("PCN", "Pitcairn");
		COUNTRIES.put("POL", "Poland");
		COUNTRIES.put("PRT", "Portugal");
		COUNTRIES.put("PRI", "Puerto Rico");
		COUNTRIES.put("QAT", "Qatar");
		COUNTRIES.put("REU", "Reunion");
		COUNTRIES.put("ROU", "Romania");
		COUNTRIES.put("RUS", "Russian Federation");
		COUNTRIES.put("RWA", "Rwanda");
		COUNTRIES.put("BLM", "Saint Barthelemy");
		COUNTRIES.put("SHN", "Saint Helena");
		COUNTRIES.put("KNA", "Saint Kitts And Nevis");
		COUNTRIES.put("LCA", "Saint Lucia");
		COUNTRIES.put("MAF", "Saint Martin");
		COUNTRIES.put("SPM", "Saint Pierre And Miquelon");
		COUNTRIES.put("VCT", "Saint Vincent And The Grenadines");
		COUNTRIES.put("WSM", "Samoa");
		COUNTRIES.put("SMR", "San Marino");
		COUNTRIES.put("STP", "Sao Tome And Principe");
		COUNTRIES.put("SAU", "Saudi Arabia");
		COUNTRIES.put("SEN", "Senegal");
		COUNTRIES.put("SRB", "Serbia");
		COUNTRIES.put("SYC", "Seychelles");
		COUNTRIES.put("SLE", "Sierra Leone");
		COUNTRIES.put("SGP", "Singapore");
		COUNTRIES.put("SVK", "Slovakia");
		COUNTRIES.put("SVN", "Slovenia");
		COUNTRIES.put("SLB", "Solomon Islands");
		COUNTRIES.put("SOM", "Somalia");
		COUNTRIES.put("ZAF", "South Africa");
		COUNTRIES.put("SGS", "South Georgia And The South Sandwich Islands");
		COUNTRIES.put("ESP", "Spain");
		COUNTRIES.put("LKA", "Sri Lanka");
		COUNTRIES.put("SDN", "Sudan");
		COUNTRIES.put("SUR", "Suriname");
		COUNTRIES.put("SJM", "Svalbard And Jan Mayen");
		COUNTRIES.put("SWZ", "Swaziland");
		COUNTRIES.put("SWE", "Sweden");
		COUNTRIES.put("CHE", "Switzerland");
		COUNTRIES.put("SYR", "Syrian Arab Republic");
		COUNTRIES.put("TWN", "Taiwan, Province Of China");
		COUNTRIES.put("TJK", "Tajikistan");
		COUNTRIES.put("TZA", "Tanzania, United Republic Of");
		COUNTRIES.put("THA", "Thailand");
		COUNTRIES.put("TLS", "Timor-Leste");
		COUNTRIES.put("TGO", "Togo");
		COUNTRIES.put("TKL", "Tokelau");
		COUNTRIES.put("TON", "Tonga");
		COUNTRIES.put("TTO", "Trinidad And Tobago");
		COUNTRIES.put("TUN", "Tunisia");
		COUNTRIES.put("TUR", "Turkey");
		COUNTRIES.put("TKM", "Turkmenistan");
		COUNTRIES.put("TCA", "Turks And Caicos Islands");
		COUNTRIES.put("TUV", "Tuvalu");
		COUNTRIES.put("UGA", "Uganda");
		COUNTRIES.put("UKR", "Ukraine");
		COUNTRIES.put("ARE", "United Arab Emirates");
		COUNTRIES.put("UMI", "United States Minor Outlying Islands");
		COUNTRIES.put("URY", "Uruguay");
		COUNTRIES.put("UZB", "Uzbekistan");
		COUNTRIES.put("VUT", "Vanuatu");
		COUNTRIES.put("VEN", "Venezuela");
		COUNTRIES.put("VNM", "Viet Nam");
		COUNTRIES.put("VGB", "Virgin Islands, British");
		COUNTRIES.put("VIR", "Virgin Islands, U.S.");
		COUNTRIES.put("WLF", "Wallis And Futuna");
		COUNTRIES.put("ESH", "Western Sahara");
		COUNTRIES.put("YEM", "Yemen");
		COUNTRIES.put("ZMB", "Zambia");
		COUNTRIES.put("ZWE", "Zimbabwe");
	}
	
	/**
	 * This method returns country name for the given country code.
	 * @param countryCode
	 * @return String
	 */
	public static String getCountryName(String countryCode){
		return COUNTRIES.get(countryCode);
	}
	
	/**
	 * This method returns list of all COUNTRIES in a Map. In the Map, Key will be 3 char country code and value will be the name of the country.
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getAllCountries(){
		return (Map<String, String>) COUNTRIES.clone();
	}
	
	public static List<String> getCountryNames(){
		return new ArrayList<String>(getAllCountries().values());
	}

	public static List<String> getCountryCodes(){
		return new ArrayList<String>(getAllCountries().keySet());
	}

}
