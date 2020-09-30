(ns claire.domain.base
  (:require [clojure.spec.alpha :as s]
            [claire.help.time :as t]))

(s/def ::ability 
  {:admin {:memo "can do everything."}
   :approver {:memo "can approve."}
   :preparer {:memo "can prepare."}
   :viewer {:memo "can view."}})

(s/def ::stance {:pay {:memo "entity takes pay side of mutual contract."}
             :receive {:memo "entity takes receive side of mutial contract."}
             :buy {:memo "entity buys a contingent contract."}
             :sell {:memo "entity sells a contingent contract."}})

(s/def ::event {:contract {:memo ".."}
            :effect {:memo ".."}
            :pay {:memo ".."}
            :receive {:memo ".."}
            :accrue {:memo ".."}
            :valuate {:memo ".."}
            :reduce {:memo ".."}
            :terminate {:memo ".."}
            :mature {:memo ".."}
            :fee {:memo ".."}})

(s/def ::span
  {:continuous {:month 0 :memo ".."}
   :month {:month 1 :memo ".."}
   :quarter {:month 3 :memo ".."}
   :semiannual {:month 6 :memo ".."}
   :annual {:month 12 :memo ".."}
   :biannual {:month 24 :memo ".."}
   :none {:month 0 :memo ".."}})

(s/def ::status
  {:posted {:memo ".."}
   :drafted {:memo ".."}
   :deleted {:memo ".."}})

(s/def ::day
  {:d30360 {:name "30360" :memo ".."}
   :dac360 {:name "AC360" :memo ".."}})

(s/def ::breed
  {:irs {:name "Interest Rate Swap"}
   :crs {:name "currency swap"}
   :cal {:name "call option"}
   :put {:name "put option"}
   :cap {:name "interest rate cap"}
   :cds {:name "credit default swap"}
   :trs {:name "total return swap"}
   :crd {:name "corridor option"}
   :spt {:name "foreign currency spot"}
   :inf {:name "inflation swap"}
   :trl {:name "treasury lock"}
   :rtr {:name "reverse treasury lock"}
   :swt {:name "swaption"}
   :cms {:name "commodity swap"}
   :ftr {:name "future"}})

(s/def ::pact
  {:irsfix {:name "interest rate swap fixed leg"}
   :irsflt {:name "interest rate swap floating leg"}})

(s/def ::ticker
  {:fixed {:memo "fixed and not use market tickers"}
   :libor1d {:memo "libor 1 day"}
   :libor1w {:memo "libor 1 week"}
   :libor2w {:memo "libor 2 weeks"}
   :libor1m {:memo "libor 1 month"}
   :libor2m {:memo "libor 2 months"}
   :libor3m {:memo "libor 3 months"}
   :libor6m {:memo "libor 6 months"}
   :libor1y {:memo "libor 1 year"}
   :euribor1w {:memo "euribor 1 week"}
   :euribor2w {:memo "euribor 2 weeks"}
   :euribor1m {:memo "euribor 1 month"}
   :euribor2m {:memo "euribor 2 months"}
   :euribor3m {:memo "euribor 3 months"}
   :euribor6m {:memo "euribor 6 months"}
   :euribor9m {:memo "euribor 9 months"}
   :euribor1y {:memo "euribor 1 year"}})

(s/def ::currency 
  {:dzd {:name "Algerian Dinar"}
   :ars {:name "Argentine Peso"}
   :aud {:name "Australian Dollar"}
   :bsd {:name "Bahamian Dollar"}
   :bhd {:name "Bahraini Dinar"}
   :bdt {:name "Taka"}
   :amd {:name "Armenian Dram"}
   :bbd {:name "Barbados Dollar"}
   :bmd {:name "Bermudian Dollar"}
   :btn {:name "Ngultrum"}
   :bob {:name "Boliviano"}
   :bwp {:name "Pula"}
   :bzd {:name "Belize Dollar"}
   :sbd {:name "Solomon Islands Dollar"}
   :bnd {:name "Brunei Dollar"}
   :mmk {:name "Kyat"}
   :bif {:name "Burundi Franc"}
   :khr {:name "Riel"}
   :cad {:name "Canadian Dollar"}
   :cve {:name "Cabo Verde Escudo"}
   :kyd {:name "Cayman Islands Dollar"}
   :lkr {:name "Sri Lanka Rupee"}
   :clp {:name "Chilean Peso"}
   :cny {:name "Yuan Renminbi"}
   :cop {:name "Colombian Peso"}
   :kmf {:name "Comorian Franc "}
   :crc {:name "Costa Rican Colon"}
   :hrk {:name "Kuna"}
   :cup {:name "Cuban Peso"}
   :czk {:name "Czech Koruna"}
   :dkk {:name "Danish Krone"}
   :dop {:name "Dominican Peso"}
   :svc {:name "El Salvador Colon"}
   :etb {:name "Ethiopian Birr"}
   :ern {:name "Nakfa"}
   :fkp {:name "Falkland Islands Pound"}
   :fjd {:name "Fiji Dollar"}
   :djf {:name "Djibouti Franc"}
   :gmd {:name "Dalasi"}
   :gip {:name "Gibraltar Pound"}
   :gtq {:name "Quetzal"}
   :gnf {:name "Guinean Franc"}
   :gyd {:name "Guyana Dollar"}
   :htg {:name "Gourde"}
   :hnl {:name "Lempira"}
   :hkd {:name "Hong Kong Dollar"}
   :huf {:name "Forint"}
   :isk {:name "Iceland Krona"}
   :inr {:name "Indian Rupee"}
   :idr {:name "Rupiah"}
   :irr {:name "Iranian Rial"}
   :iqd {:name "Iraqi Dinar"}
   :ils {:name "New Israeli Sheqel"}
   :jmd {:name "Jamaican Dollar"}
   :jpy {:name "Yen"}
   :kzt {:name "Tenge"}
   :jod {:name "Jordanian Dinar"}
   :kes {:name "Kenyan Shilling"}
   :kpw {:name "North Korean Won"}
   :krw {:name "Won"}
   :kwd {:name "Kuwaiti Dinar"}
   :kgs {:name "Som"}
   :lak {:name "Lao Kip"}
   :lbp {:name "Lebanese Pound"}
   :lsl {:name "Loti"}
   :lrd {:name "Liberian Dollar"}
   :lyd {:name "Libyan Dinar"}
   :mop {:name "Pataca"}
   :mwk {:name "Malawi Kwacha"}
   :myr {:name "Malaysian Ringgit"}
   :mvr {:name "Rufiyaa"}
   :mur {:name "Mauritius Rupee"}
   :mxn {:name "Mexican Peso"}
   :mnt {:name "Tugrik"}
   :mdl {:name "Moldovan Leu"}
   :mad {:name "Moroccan Dirham"}
   :omr {:name "Rial Omani"}
   :nad {:name "Namibia Dollar"}
   :npr {:name "Nepalese Rupee"}
   :ang {:name "Netherlands Antillean Guilder"}
   :awg {:name "Aruban Florin"}
   :vuv {:name "Vatu"}
   :nzd {:name "New Zealand Dollar"}
   :nio {:name "Cordoba Oro"}
   :ngn {:name "Naira"}
   :nok {:name "Norwegian Krone"}
   :pkr {:name "Pakistan Rupee"}
   :pab {:name "Balboa"}
   :pgk {:name "Kina"}
   :pyg {:name "Guarani"}
   :pen {:name "Sol"}
   :php {:name "Philippine Peso"}
   :qar {:name "Qatari Rial"}
   :rub {:name "Russian Ruble"}
   :rwf {:name "Rwanda Franc"}
   :shp {:name "Saint Helena Pound"}
   :sar {:name "Saudi Riyal"}
   :scr {:name "Seychelles Rupee"}
   :sll {:name "Leone"}
   :sgd {:name "Singapore Dollar"}
   :vnd {:name "Dong"}
   :sos {:name "Somali Shilling"}
   :zar {:name "Rand"}
   :ssp {:name "South Sudanese Pound"}
   :szl {:name "Lilangeni"}
   :sek {:name "Swedish Krona"}
   :chf {:name "Swiss Franc"}
   :syp {:name "Syrian Pound"}
   :thb {:name "Baht"}
   :top {:name "Pa’anga"}
   :ttd {:name "Trinidad and Tobago Dollar"}
   :aed {:name "UAE Dirham"}
   :tnd {:name "Tunisian Dinar"}
   :ugx {:name "Uganda Shilling"}
   :mkd {:name "Denar"}
   :egp {:name "Egyptian Pound"}
   :gbp {:name "Pound Sterling"}
   :tzs {:name "Tanzanian Shilling"}
   :usd {:name "US Dollar"}
   :uyu {:name "Peso Uruguayo"}
   :uzs {:name "Uzbekistan Sum"}
   :wst {:name "Tala"}
   :yer {:name "Yemeni Rial"}
   :twd {:name "New Taiwan Dollar"}
   :uyw {:name "Unidad Previsional"}
   :ves {:name "Bolívar Soberano"}
   :mru {:name "Ouguiya"}
   :stn {:name "Dobra"}
   :cuc {:name "Peso Convertible"}
   :zwl {:name "Zimbabwe Dollar"}
   :byn {:name "Belarusian Ruble"}
   :tmt {:name "Turkmenistan New Manat"}
   :ghs {:name "Ghana Cedi"}
   :sdg {:name "Sudanese Pound"}
   :uyi {:name "Uruguay Peso en Unidades Indexadas (UI)"}
   :rsd {:name "Serbian Dinar"}
   :mzn {:name "Mozambique Metical"}
   :azn {:name "Azerbaijan Manat"}
   :ron {:name "Romanian Leu"}
   :che {:name "WIR Euro"}
   :chw {:name "WIR Franc"}
   :try {:name "Turkish Lira"}
   :xaf {:name "CFA Franc BEAC"}
   :xcd {:name "East Caribbean Dollar"}
   :xof {:name "CFA Franc BCEAO"}
   :xpf {:name "CFP Franc"}
   :xba {:name "Bond Markets Unit European Composite Unit (EURCO)"}
   :xbb {:name "Bond Markets Unit European Monetary Unit (E.M.U.-6)"}
   :xbc {:name "Bond Markets Unit European Unit of Account 9 (E.U.A.-9)"}
   :xbd {:name "Bond Markets Unit European Unit of Account 17 (E.U.A.-17)"}
   :xau {:name "Gold"}
   :xdr {:name "SDR (Special Drawing Right)"}
   :xag {:name "Silver"}
   :xpt {:name "Platinum"}
   :xts {:name "Codes specifically reserved for testing purposes"}
   :xpd {:name "Palladium"}
   :xua {:name "ADB Unit of Account"}
   :zmw {:name "Zambian Kwacha"}
   :srd {:name "Surinam Dollar"}
   :mga {:name "Malagasy Ariary"}
   :cou {:name "Unidad de Valor Real"}
   :afn {:name "Afghani"}
   :tjs {:name "Somoni"}
   :aoa {:name "Kwanza"}
   :bgn {:name "Bulgarian Lev"}
   :cdf {:name "Congolese Franc"}
   :bam {:name "Convertible Mark"}
   :eur {:name "Euro"}
   :mxv {:name "Mexican Unidad de Inversion (UDI)"}
   :uah {:name "Hryvnia"}
   :gel {:name "Lari"}
   :bov {:name "Mvdol"}
   :pln {:name "Zloty"}
   :brl {:name "Brazilian Real"}
   :clf {:name "Unidad de Fomento"}
   :xsu {:name "Sucre"}
   :usn {:name "US Dollar (Next day)"}
   :xxx {:name "The codes assigned for transactions where no currency is involved"}})

(defn make-quote
  [{:keys [ticker number]}]
  (let [quote
        {:ticker (if (string? ticker)
                   (keyword ticker)
                   (s/conform ::ticker ticker))
         :number (s/conform double? number)}]
    quote))

(defn make-money
  [{:keys [currency number]}]
  (let [money
        {:ticker (if (string? currency)
                   (keyword currency)
                   (s/conform ::currency currency))
         :number (s/conform double? number)}]
    money))

(s/def ::id
  (s/and int? #(> % 0)))

(s/def ::count
  (s/and int? #(> % 0)))

(s/def ::name
  (s/and string?))

(s/def ::memo
  (s/and string?))

(s/def ::actual
  (s/and boolean?))

(defn make-leg
  [{:keys [id name pact stance period span day
           contractnum tickerfixed fixedrate tickerfloating
           notionalcurrency notionalamount memo]}]
  {:id (s/conform ::id id) 
   :name (s/conform ::name name) 
   :pact (s/conform ::pact pact) 
   :stance (s/conform ::stance stance)
   :period (s/conform ::count period) 
   :span (s/conform ::span span) 
   :day (s/conform ::day day) 
   :contractnum  (s/conform ::count contractnum) 
   :fixedquote (make-quote {:ticker tickerfixed :number fixedrate})
   :tickerfloating (s/conform ::ticker tickerfloating)
   :notional (make-money {:currency notionalcurrency :number notionalamount}) 
   :memo (s/conform ::memo memo)
   })

(defn make-deal
  [{:keys [id name breed tradedate effectdate
           terminatedate maturedate leg memo]}]
  {:id (s/conform ::id id) 
   :name (s/conform ::name name) 
   :breed (s/conform ::breed breed) 
   :tradedate tradedate
   :effectdate effectdate
   :terminatedate (if (nil? terminatedate) 
                    nil 
                    terminatedate)
   :maturedate maturedate
   :leg (s/conform vector? leg)
   :memo (s/conform ::memo memo) 
   })

(defn make-proj
  [{:keys [id date dealid legid event
           periodstart periodend interestticker
           interestnumber notionalcurrency
           notionalamount actual]}]
  {:id (s/conform ::id id)
   :date (t/ensure-date-type date)
   :dealid (s/conform ::id dealid)
   :legid (s/conform ::id legid)
   :event (s/conform ::event event)
   :periodstart (t/ensure-date-type periodstart)
   :periodend (t/ensure-date-type periodend)
   :daysinperiod (t/until {:earlier periodstart :later periodend})
   :interestquote (make-quote {:ticker interestticker :number interestnumber})
   :notional (make-money {:currency notionalcurrency :number notionalamount})
   :actual (s/conform ::actual actual)
   })


;;;;;;;;;;;;;;;;

(def testleg
  (make-leg {:id 12
             :name "abc"
             :pact :irsfix
             :stance :receive
             :period 10
             :span :annual
             :day :dac360
             :contractnum 1
             :tickerfixed :libor1y
             :fixedrate 0.0123
             :tickerfloating :libor6m
             :notionalcurrency :usd
             :notionalamount 12.12
             :memo "AA"}))

(def mydeal
  (make-deal {:id 123
              :name "Abcd deal"
              :breed :irs
              :tradedate "2020-02-02"
              :effectdate "2020-04-12"
              :terminatedate nil
              :maturedate "2020-02-22"
              :leg [testleg]
              :memo "asdf"
              }))

(def myproj
  (make-proj {:id 222
              :date "2019-11-22"
              :dealid 1
              :legid 2
              :event :contract
              :periodstart "2019-06-12"
              :periodend "2019-08-22"
              :interestticker :libor1y
              :interestnumber 0.23
              :notionalcurrency :eur
              :notionalamount 100000.0
              :actual false }))