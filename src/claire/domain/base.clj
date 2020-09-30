(ns claire.domain.base
  (:require [clojure.spec.alpha :as s]
            [claire.help.time :as t]))

(defn get-ability [key]
  (case key
    :admin {:key :admin :memo "can do everything."}
    :approver {:key :approver :memo "can approve."}
    :preparer {:key :preparer :memo "can prepare."}
    :viewer {:key :viewer :memo "can view."}
    :none))

(defn get-stance [key]
  (case key
    :pay {:key :pay :memo "entity takes pay side of mutual contract."}
    :receive {:key :receive :memo "entity takes receive side of mutial contract."}
    :buy {:key :buy :memo "entity buys a contingent contract."}
    :sell {:key :sell :memo "entity sells a contingent contract."}
    :none))

(defn get-event [key]
  (case key
    :contract {:key :contract :memo ".."}
    :effect {:key :effect :memo ".."}
    :interest {:key :interest :memo ".."}
    :reduce {:key :reduce :memo ".."}
    :terminate {:key :terminate :memo ".."}
    :mature {:key :mature :memo ".."}
    :none))

(defn get-span [key]
  (case key
    :continuous {:key :continuous  
                 :num-month 0 
                 :year-frac (/ 0 12)}
    :month {:key :month
            :num-month 1
            :year-frac (/ 1 12)}
    :quarter {:key :quarter 
              :num-month 3   
              :year-frac (/ 3 12)}
    :semiannual {:key :semiannual  
                 :num-month 6 
                 :year-frac (/ 6 12)}
    :annual {:key :annual 
             :num-month 12  
             :year-frac (/ 12 12)}
    :biannual {:key  :biannual 
               :num-month 24  
               :year-frac (/ 24 12)}
    :none))

(defn get-status [key]
  (case key
    :posted {:key :posted :memo ".."}
    :drafted {:key :drafted :memo ".."}
    :deleted {:key :deleted :memo ".."}
    :none))

(defn get-day [key]
  (case key
    :d30360 {:key :d30360 :name "30360" :memo ".."}
    :dac360 {:key :dac360 :name "AC360" :memo ".."}))

(defn get-breed [key]
  (case key
    :irs {:key :irs :name "Interest Rate Swap"}
    :crs {:key :crs :name "currency swap"}
    :cal {:key :cal :name "call option"}
    :put {:key :put :name "put option"}
    :cap {:key :cap :name "interest rate cap"}
    :cds {:key :cds :name "credit default swap"}
    :trs {:key :trs :name "total return swap"}
    :crd {:key :crd :name "corridor option"}
    :spt {:key :spt :name "foreign currency spot"}
    :inf {:key :inf :name "inflation swap"}
    :trl {:key :trl :name "treasury lock"}
    :rtr {:key :rtr :name "reverse treasury lock"}
    :swt {:key :swt :name "swaption"}
    :cms {:key :cms :name "commodity swap"}
    :ftr {:key :ftr :name "future"}
    :none))

(defn get-pact [key]
  (case key
    :irsfix {:key :irsfix :name "interest rate swap fixed leg"}
    :irsflt {:key :irsflt :name "interest rate swap floating leg"}
    :none))

(defn get-ticker [key]
  (case key
    :fixed {:key :fixed :memo "fixed and not use market tickers"}
    :libor1d {:key :libor1d :memo "libor 1 day"}
    :libor1w {:key :libor1w :memo "libor 1 week"}
    :libor2w {:key :libor2w :memo "libor 2 weeks"}
    :libor1m {:key :libor1m :memo "libor 1 month"}
    :libor2m {:key :libor2m :memo "libor 2 months"}
    :libor3m {:key :libor3m :memo "libor 3 months"}
    :libor6m {:key :libor6m :memo "libor 6 months"}
    :libor1y {:key :libor1y :memo "libor 1 year"}
    :euribor1w {:key :euribor1w :memo "euribor 1 week"}
    :euribor2w {:key :euribor2w :memo "euribor 2 weeks"}
    :euribor1m {:key :euribor1m :memo "euribor 1 month"}
    :euribor2m {:key :euribor2m :memo "euribor 2 months"}
    :euribor3m {:key :euribor3m :memo "euribor 3 months"}
    :euribor6m {:key :euribor6m :memo "euribor 6 months"}
    :euribor9m {:key :euribor9m :memo "euribor 9 months"}
    :euribor1y {:key :euribor1y :memo "euribor 1 year"}
    :none))

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


(defn make-id [n]
  (if (and (int? n)
           #(> % 0))
    n :fail))

(defn make-count [n]
  (if (and (int? n)
           #(> % 0))
    n :fail))

(defn make-name [s]
  (if (string? s) s :fail))

(defn make-memo [s]
  (if (string? s) s :fail))

(defn make-actual [b]
  (if (boolean? b) b :fail))

(defn make-leg
  [{:keys [id name pact stance period span day
           contractnum tickerfixed fixedrate tickerfloating
           notionalcurrency notionalamount memo]}]
  {:id (make-id id) 
   :name (make-name name) 
   :pact (get-pact pact)
   :stance (get-stance stance)
   :period (make-count period) 
   :span (get-span span) 
   :day (get-day day) 
   :contractnum  (s/conform ::count contractnum) 
   :fixedquote (make-quote {:ticker tickerfixed :number fixedrate})
   :tickerfloating (get-ticker tickerfloating)
   :notional (make-money {:currency notionalcurrency :number notionalamount}) 
   :memo (make-memo memo)
   })

(defn make-deal
  [{:keys [id name breed tradedate effectdate
           terminatedate maturedate leg memo]}]
  {:id (make-id id) 
   :name (make-name name)
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
  {:id (make-id id)
   :date (t/ensure-date-string date)
   :dealid (make-id dealid)
   :legid (make-id legid)
   :event (get-event event)
   :periodstart (if (nil? periodstart) nil (t/ensure-date-string periodstart))
   :periodend (if (nil? periodend) nil (t/ensure-date-string periodend))
   :daysinperiod (if (nil? periodstart) nil (t/until {:earlier periodstart :later periodend}))
   :interestquote (make-quote {:ticker interestticker :number interestnumber})
   :notional (make-money {:currency notionalcurrency :number notionalamount})
   :actual (make-actual actual)
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
              :leg [testleg testleg]
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