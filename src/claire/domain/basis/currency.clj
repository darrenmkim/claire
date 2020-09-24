(ns claire.domain.basis.currency
  (:require [claire.center.db :as db]))

(defn schema []
  (str "create table if not exists "
       "currency ("
       "id text primary key, "
       "name text not null)"))

(defn preval []
  [{:id "dzd", :name "Algerian Dinar"}
   {:id "ars", :name "Argentine Peso"}
   {:id "aud", :name "Australian Dollar"}
   {:id "bsd", :name "Bahamian Dollar"}
   {:id "bhd", :name "Bahraini Dinar"}
   {:id "bdt", :name "Taka"}
   {:id "amd", :name "Armenian Dram"}
   {:id "bbd", :name "Barbados Dollar"}
   {:id "bmd", :name "Bermudian Dollar"}
   {:id "btn", :name "Ngultrum"}
   {:id "bob", :name "Boliviano"}
   {:id "bwp", :name "Pula"}
   {:id "bzd", :name "Belize Dollar"}
   {:id "sbd", :name "Solomon Islands Dollar"}
   {:id "bnd", :name "Brunei Dollar"}
   {:id "mmk", :name "Kyat"}
   {:id "bif", :name "Burundi Franc"}
   {:id "khr", :name "Riel"}
   {:id "cad", :name "Canadian Dollar"}
   {:id "cve", :name "Cabo Verde Escudo"}
   {:id "kyd", :name "Cayman Islands Dollar"}
   {:id "lkr", :name "Sri Lanka Rupee"}
   {:id "clp", :name "Chilean Peso"}
   {:id "cny", :name "Yuan Renminbi"}
   {:id "cop", :name "Colombian Peso"}
   {:id "kmf", :name "Comorian Franc "}
   {:id "crc", :name "Costa Rican Colon"}
   {:id "hrk", :name "Kuna"}
   {:id "cup", :name "Cuban Peso"}
   {:id "czk", :name "Czech Koruna"}
   {:id "dkk", :name "Danish Krone"}
   {:id "dop", :name "Dominican Peso"}
   {:id "svc", :name "El Salvador Colon"}
   {:id "etb", :name "Ethiopian Birr"}
   {:id "ern", :name "Nakfa"}
   {:id "fkp", :name "Falkland Islands Pound"}
   {:id "fjd", :name "Fiji Dollar"}
   {:id "djf", :name "Djibouti Franc"}
   {:id "gmd", :name "Dalasi"}
   {:id "gip", :name "Gibraltar Pound"}
   {:id "gtq", :name "Quetzal"}
   {:id "gnf", :name "Guinean Franc"}
   {:id "gyd", :name "Guyana Dollar"}
   {:id "htg", :name "Gourde"}
   {:id "hnl", :name "Lempira"}
   {:id "hkd", :name "Hong Kong Dollar"}
   {:id "huf", :name "Forint"}
   {:id "isk", :name "Iceland Krona"}
   {:id "inr", :name "Indian Rupee"}
   {:id "idr", :name "Rupiah"}
   {:id "irr", :name "Iranian Rial"}
   {:id "iqd", :name "Iraqi Dinar"}
   {:id "ils", :name "New Israeli Sheqel"}
   {:id "jmd", :name "Jamaican Dollar"}
   {:id "jpy", :name "Yen"}
   {:id "kzt", :name "Tenge"}
   {:id "jod", :name "Jordanian Dinar"}
   {:id "kes", :name "Kenyan Shilling"}
   {:id "kpw", :name "North Korean Won"}
   {:id "krw", :name "Won"}
   {:id "kwd", :name "Kuwaiti Dinar"}
   {:id "kgs", :name "Som"}
   {:id "lak", :name "Lao Kip"}
   {:id "lbp", :name "Lebanese Pound"}
   {:id "lsl", :name "Loti"}
   {:id "lrd", :name "Liberian Dollar"}
   {:id "lyd", :name "Libyan Dinar"}
   {:id "mop", :name "Pataca"}
   {:id "mwk", :name "Malawi Kwacha"}
   {:id "myr", :name "Malaysian Ringgit"}
   {:id "mvr", :name "Rufiyaa"}
   {:id "mur", :name "Mauritius Rupee"}
   {:id "mxn", :name "Mexican Peso"}
   {:id "mnt", :name "Tugrik"}
   {:id "mdl", :name "Moldovan Leu"}
   {:id "mad", :name "Moroccan Dirham"}
   {:id "omr", :name "Rial Omani"}
   {:id "nad", :name "Namibia Dollar"}
   {:id "npr", :name "Nepalese Rupee"}
   {:id "ang", :name "Netherlands Antillean Guilder"}
   {:id "awg", :name "Aruban Florin"}
   {:id "vuv", :name "Vatu"}
   {:id "nzd", :name "New Zealand Dollar"}
   {:id "nio", :name "Cordoba Oro"}
   {:id "ngn", :name "Naira"}
   {:id "nok", :name "Norwegian Krone"}
   {:id "pkr", :name "Pakistan Rupee"}
   {:id "pab", :name "Balboa"}
   {:id "pgk", :name "Kina"}
   {:id "pyg", :name "Guarani"}
   {:id "pen", :name "Sol"}
   {:id "php", :name "Philippine Peso"}
   {:id "qar", :name "Qatari Rial"}
   {:id "rub", :name "Russian Ruble"}
   {:id "rwf", :name "Rwanda Franc"}
   {:id "shp", :name "Saint Helena Pound"}
   {:id "sar", :name "Saudi Riyal"}
   {:id "scr", :name "Seychelles Rupee"}
   {:id "sll", :name "Leone"}
   {:id "sgd", :name "Singapore Dollar"}
   {:id "vnd", :name "Dong"}
   {:id "sos", :name "Somali Shilling"}
   {:id "zar", :name "Rand"}
   {:id "ssp", :name "South Sudanese Pound"}
   {:id "szl", :name "Lilangeni"}
   {:id "sek", :name "Swedish Krona"}
   {:id "chf", :name "Swiss Franc"}
   {:id "syp", :name "Syrian Pound"}
   {:id "thb", :name "Baht"}
   {:id "top", :name "Pa’anga"}
   {:id "ttd", :name "Trinidad and Tobago Dollar"}
   {:id "aed", :name "UAE Dirham"}
   {:id "tnd", :name "Tunisian Dinar"}
   {:id "ugx", :name "Uganda Shilling"}
   {:id "mkd", :name "Denar"}
   {:id "egp", :name "Egyptian Pound"}
   {:id "gbp", :name "Pound Sterling"}
   {:id "tzs", :name "Tanzanian Shilling"}
   {:id "usd", :name "US Dollar"}
   {:id "uyu", :name "Peso Uruguayo"}
   {:id "uzs", :name "Uzbekistan Sum"}
   {:id "wst", :name "Tala"}
   {:id "yer", :name "Yemeni Rial"}
   {:id "twd", :name "New Taiwan Dollar"}
   {:id "uyw", :name "Unidad Previsional"}
   {:id "ves", :name "Bolívar Soberano"}
   {:id "mru", :name "Ouguiya"}
   {:id "stn", :name "Dobra"}
   {:id "cuc", :name "Peso Convertible"}
   {:id "zwl", :name "Zimbabwe Dollar"}
   {:id "byn", :name "Belarusian Ruble"}
   {:id "tmt", :name "Turkmenistan New Manat"}
   {:id "ghs", :name "Ghana Cedi"}
   {:id "sdg", :name "Sudanese Pound"}
   {:id "uyi", :name "Uruguay Peso en Unidades Indexadas (UI)"}
   {:id "rsd", :name "Serbian Dinar"}
   {:id "mzn", :name "Mozambique Metical"}
   {:id "azn", :name "Azerbaijan Manat"}
   {:id "ron", :name "Romanian Leu"}
   {:id "che", :name "WIR Euro"}
   {:id "chw", :name "WIR Franc"}
   {:id "try", :name "Turkish Lira"}
   {:id "xaf", :name "CFA Franc BEAC"}
   {:id "xcd", :name "East Caribbean Dollar"}
   {:id "xof", :name "CFA Franc BCEAO"}
   {:id "xpf", :name "CFP Franc"}
   {:id "xba", :name "Bond Markets Unit European Composite Unit (EURCO)"}
   {:id "xbb", :name "Bond Markets Unit European Monetary Unit (E.M.U.-6)"}
   {:id "xbc", :name "Bond Markets Unit European Unit of Account 9 (E.U.A.-9)"}
   {:id "xbd", :name "Bond Markets Unit European Unit of Account 17 (E.U.A.-17)"}
   {:id "xau", :name "Gold"}
   {:id "xdr", :name "SDR (Special Drawing Right)"}
   {:id "xag", :name "Silver"}
   {:id "xpt", :name "Platinum"}
   {:id "xts", :name "Codes specifically reserved for testing purposes"}
   {:id "xpd", :name "Palladium"}
   {:id "xua", :name "ADB Unit of Account"}
   {:id "zmw", :name "Zambian Kwacha"}
   {:id "srd", :name "Surinam Dollar"}
   {:id "mga", :name "Malagasy Ariary"}
   {:id "cou", :name "Unidad de Valor Real"}
   {:id "afn", :name "Afghani"}
   {:id "tjs", :name "Somoni"}
   {:id "aoa", :name "Kwanza"}
   {:id "bgn", :name "Bulgarian Lev"}
   {:id "cdf", :name "Congolese Franc"}
   {:id "bam", :name "Convertible Mark"}
   {:id "eur", :name "Euro"}
   {:id "mxv", :name "Mexican Unidad de Inversion (UDI)"}
   {:id "uah", :name "Hryvnia"}
   {:id "gel", :name "Lari"}
   {:id "bov", :name "Mvdol"}
   {:id "pln", :name "Zloty"}
   {:id "brl", :name "Brazilian Real"}
   {:id "clf", :name "Unidad de Fomento"}
   {:id "xsu", :name "Sucre"}
   {:id "usn", :name "US Dollar (Next day)"}
   {:id "xxx", :name "The codes assigned for transactions where no currency is involved"}])

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :currency (preval))
  (println "<currency> table is set up."))
