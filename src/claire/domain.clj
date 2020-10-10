(ns claire.domain
  (:require
   ;;[clojure.spec.alpha :as sp]
   ;;[claire.time :as tm]
   [claire.db :as db]))



;;;; static ;;;;

(def static
  {:ability-schema  (str "create table if not exists "
                         "ability ("
                         "id text primary key, "
                         "memo text not null)")
   :ability-value  [{:id "admin"    :memo "can do everything."}
                    {:id "approver" :memo "can approve."}
                    {:id "preparer" :memo "can prepare."}
                    {:id "viewer"   :memo "can view."}]
   :stance-schema   (str "create table if not exists "
                         "stance ("
                         "id text primary key, "
                         "memo text not null)")
   :stance-value   [{:id "pay"     :memo "entity takes pay side of mutual contract."}
                    {:id "receive" :memo "entity takes receive side of mutial contract."}
                    {:id "buy"     :memo "entity buys a contingent contract."}
                    {:id "sell"    :memo "entity sells a contingent contract."}]
   :event-schema    (str "create table if not exists "
                         "event ("
                         "id text primary key, "
                         "memo text not null)")
   :event-value    [{:id "contract"  :memo ".."}
                    {:id "effect"    :memo ".."}
                    {:id "interest"  :memo ".."}
                    {:id "reduce"    :memo ".."}
                    {:id "terminate" :memo ".."}
                    {:id "mature"    :memo ".."}]
   :span-schema     (str "create table if not exists "
                         "span ("
                         "id text primary key, "
                         "month_count smallint unique not null, "
                         "year_fraction real unique not null)")
   :span-value     [{:id "continuous" :month_count 0  :year_fraction (float (/ 0 12))}
                    {:id "month"      :month_count 1  :year_fraction (float (/ 1 12))}
                    {:id "quarter"    :month_count 3  :year_fraction (float (/ 3 12))}
                    {:id "semiannual" :month_count 6  :year_fraction (float (/ 6 12))}
                    {:id "annual"     :month_count 12 :year_fraction (float (/ 12 12))}
                    {:id "biannual"   :month_count 24 :year_fraction (float (/ 24 12))}]
   :status-schema   (str "create table if not exists "
                         "status ("
                         "id text primary key, "
                         "memo text not null)")
   :status-value   [{:id "posted"  :memo ".."}
                    {:id "drafted" :memo ".."}
                    {:id "deleted" :memo ".."}]
   :day-schema      (str "create table if not exists "
                         "day ("
                         "id text primary key, "
                         "name text unique not null, "
                         "memo text not null)")
   :day-value      [{:id "d30360" :name "30360" :memo ".."}
                    {:id "dac360" :name "AC360" :memo ".."}]
   :breed-schema    (str "create table if not exists "
                         "breed ("
                         "id text primary key, "
                         "name text not null)")
   :breed-value    [{:id "irs" :name "interest rate swap"}
                    {:id "crs" :name "currency swap"}
                    {:id "cal" :name "call option"}
                    {:id "put" :name "put option"}
                    {:id "cap" :name "interest rate cap"}
                    {:id "cds" :name "credit default swap"}
                    {:id "trs" :name "total return swap"}
                    {:id "crd" :name "corridor option"}
                    {:id "spt" :name "foreign currency spot"}
                    {:id "inf" :name "inflation swap"}
                    {:id "trl" :name "treasury lock"}
                    {:id "rtr" :name "reverse treasury lock"}
                    {:id "swt" :name "swaption"}
                    {:id "cms" :name "commodity swap"}
                    {:id "ftr" :name "future"}]
   :pact-schema     (str "create table if not exists "
                         "pact ("
                         "id text primary key, "
                         "name text not null)")
   :pact-value     [{:id "irsfix" :name "interest rate swap fixed leg"}
                    {:id "irsflt" :name "interest rate swap floating leg"}]
   :strategy-schema (str "create table if not exists "
                         "strategy ("
                         "id text primary key, "
                         "name text unique not null, "
                         "parent_breed_id text references breed(id) not null, "
                         "receive_pact_id text references pact(id) not null, "
                         "pay_pact_id text references pact(id) not null, "
                         "memo text not null)")
   :strategy-value [{:id "mvhirs" 
                     :name "market value hedge interest rate swap" :parent_breed_id "irs" 
                     :receive_pact_id "irsfix" :pay_pact_id "irsflt" :memo ".."}
                    {:id "cfhirs" 
                     :name "cashflow hedge interst rate swap" :parent_breed_id "irs" 
                     :receive_pact_id "irsfix" :pay_pact_id "irsflt" :memo ".."}]
   :ticker-schema (str "create table if not exists "
                       "ticker ("
                       "id text primary key, "
                       "memo text not null)")
   :ticker-value [{:id "fixed"     :memo "fixed and not use market tickers"}
                  {:id "libor1d"   :memo "libor 1 day"}
                  {:id "libor1w"   :memo "libor 1 week"}
                  {:id "libor2w"   :memo "libor 2 weeks"}
                  {:id "libor1m"   :memo "libor 1 month"}
                  {:id "libor2m"   :memo "libor 2 months"}
                  {:id "libor3m"   :memo "libor 3 months"}
                  {:id "libor6m"   :memo "libor 6 months"}
                  {:id "libor1y"   :memo "libor 1 year"}
                  {:id "euribor1w" :memo "euribor 1 week"}
                  {:id "euribor2w" :memo "euribor 2 weeks"}
                  {:id "euribor1m" :memo "euribor 1 month"}
                  {:id "euribor2m" :memo "euribor 2 months"}
                  {:id "euribor3m" :memo "euribor 3 months"}
                  {:id "euribor6m" :memo "euribor 6 months"}
                  {:id "euribor9m" :memo "euribor 9 months"}
                  {:id "euribor1y" :memo "euribor 1 year"}]
   :currency-schema (str "create table if not exists "
                         "currency ("
                         "id text primary key, "
                         "name text not null)")
   :currency-value [{:id "cad" :name "Canadian Dollar"}
                    {:id "gbp" :name "Pound Sterling"}
                    {:id "krw" :name "Won"}
                    {:id "usd" :name "US Dollar"}
                    {:id "eur" :name "Euro"}]})


;;;; dynamic ;;;; 

(def dynamic
  {:client-schema   (str "create table if not exists "
                         "client ("
                         "id serial primary key, "
                         "short_name text not null, "
                         "legal_name text not null, "
                         "base_currency text references currency(id) not null, "
                         "functional_currency text references currency(id) not null, "
                         "reporting_currency text references currency(id) not null, "
                         "memo text)")
   :client-make          '(defn make-client
                            [{:keys [id short_name legal_name base_currency
                                     functional_currency reporting_currency memo]}]
                            {:id id
                             :short_name short_name
                             :legal_name legal_name
                             :base_currency base_currency
                             :functional_currency functional_currency
                             :reporting_currency reporting_currency
                             :memo memo})
   :person-schema   (str "create table if not exists "
                         "person ("
                         "id serial primary key, "
                         "client_id int references client(id) not null, "
                         "username text unique not null, "
                         "email text unique not null, "
                         "first_name text not null, "
                         "last_name text not null, "
                         "phone text)")
   :person-make          '(defn make-person
                            [{:keys [id client_id username email
                                     first_name last_name phone]}]
                            {:id id
                             :client_id client_id
                             :username username 
                             :email email
                             :first_name first_name 
                             :last_name last_name
                             :phone phone})
   :category-schema (str "create type "
                         "category_class as enum ("
                         "'a', 'b', 'c', 'd', 'e', 'f', 'g'); "
                         "create table if not exists "
                         "category ("
                         "id text primary key, "
                         "class category_class not null, "
                         "client_id int references client(id) not null)")
   :category-make        '(defn make-category
                            [{:keys [id class client_id]}]
                            {:id id
                             :class class
                             :client_id client_id})                     
   :account-schema  (str "create table if not exists "
                         "account ("
                         "id serial primary key, "
                         "client_id int references client(id) not null, "
                         "account_name text unique not null, "
                         "account_number text unique not null, "
                         "active boolean not null, "
                         "memo text not null)")
   :account-make         '(defn make-account
                            [{:keys [id client_id account_name
                                     account_number active memo]}]
                            {:id id
                             :client_id client_id
                             :account_name account_name
                             :account_number account_number
                             :active active
                             :memo memo}) 
   :matrix-schema   (str "create table if not exists "
                         "matrix " "( "
                         "id serial primary key, "
                         "name text not null unique, "
                         "pact_id text references pact(id) not null , "
                         "event_id text references event(id) not null , "
                         "account_id int references account(id) not null , "
                         "direction boolean not null, "
                         "memo text not null)")
   :quote-schema    (str "create table if not exists "
                         "quote ("
                         "id serial primary key, "
                         "date date not null, "
                         "ticker_id text references ticker(id) not null, "
                         "rate real not null, "
                         "memo text)")
   :deal-schema     (str "create table if not exists "
                         "deal ("
                         "id serial primary key, "
                         "deal_name text unique not null, "
                         "category_a text references category(id) not null, "
                         "category_b text references category(id) not null, "
                         "category_c text references category(id) not null, "
                         "category_d text references category(id) not null, "
                         "category_e text references category(id) not null, "
                         "category_f text references category(id) not null, "
                         "category_g text references category(id) not null, "
                         "breed_id text references breed(id) not null, "
                         "trade_date date not null, "
                         "effect_date date not null, "
                         "terminate_date date not null, "
                         "mature_date date not null, "
                         "memo text not null)")
   :leg-schema      (str "create table if not exists "
                         "leg ("
                         "id serial primary key, "
                         "leg_name text unique not null, "
                         "trade_date date not null, "
                         "effect_date date not null, "
                         "terminate_date date not null, "
                         "mature_date date not null, "                         
                         "deal_id int references deal(id) not null, "
                         "pact_id text references pact(id) not null, "
                         "stance_id text references stance(id) not null, "
                         "num_contract int not null, "
                         "period_count smallint not null, "
                         "span_id text references span(id) not null, "
                         "day_id text references day(id) not null, "
                         "notional_currency text references currency(id) not null, "
                         "notional_amount real not null, "
                         "fixed_rate_ticker text references ticker(id) not null, "
                         "fixed_rate_number real, "
                         "memo text)")
   :roll-schema     (str "create table if not exists "
                         "roll ("
                         "id serial primary key, "
                         "order_timestamp timestamptz not null, "
                         "system_start_date date not null, "
                         "system_end_date date not null, "
                         "client_id int references client(id) not null, "
                         "status_id text references status(id) not null, "
                         "memo text)")
   :proj-schema     (str "create table if not exists "
                         "proj ("
                         "id serial primary key, "
                         "proj_date date not null, "
                         "leg_id int references leg(id) not null, "
                         "event_id text references event(id) not null, "
                         "notional_currency text references currency(id) not null, "
                         "notional_amount real not null, "
                         "accrue_start_date date, "
                         "accrue_end_date date, "
                         "interest_rate_ticker text references ticker(id), "
                         "interest_rate_number real, "
                         "amount real not null, "
                         "memo text not null)")
   :tran-record          '(defrecord tran [id date leg_id event_id notional_currency notional_amount accrue_start_date
                                          accrue_end_date date interest_rate_ticker interest_rate_number amount memo])
   :tran-schema     (str "create table if not exists "
                         "tran ("
                         "id serial primary key, "
                         "date date not null, "
                         "leg_id int references leg(id) not null, "
                         "event_id text references event(id) not null, "
                         "notional_currency text references currency(id) not null, "
                         "notional_amount real not null, "
                         "accrue_start_date date, "
                         "accrue_end_date date, "
                         "interest_rate_ticker text references ticker(id), "
                         "interest_rate_number real, "
                         "amount real not null, "
                         "memo text not null)")
   :journal-record       '(defrecord journal [id name])
   :journal-schema  (str "create table if not exists "
                         "journal ("
                         "id serial primary key, "
                         "tran_id int references tran(id) not null, "
                         "account_id int references account(id) not null, "
                         "entry_currency text references currency(id) not null, "
                         "entry_amount real not null, "
                         "memo text)")})


;;;; set ;;;;

(defn set-static! []
  (db/execute! (:ability-schema static))
  (db/execute! (:stance-schema static))
  (db/execute! (:event-schema static))
  (db/execute! (:span-schema static))
  (db/execute! (:status-schema static))
  (db/execute! (:day-schema static))
  (db/execute! (:breed-schema static))
  (db/execute! (:pact-schema static))
  (db/execute! (:strategy-schema static))
  (db/execute! (:ticker-schema static))
  (db/execute! (:currency-schema static))
  (db/insert-pre! :ability (:ability-value static))
  (db/insert-pre! :stance (:stance-value static))
  (db/insert-pre! :event (:event-value static))
  (db/insert-pre! :span (:span-value static))
  (db/insert-pre! :status (:status-value static))
  (db/insert-pre! :day (:day-value static))
  (db/insert-pre! :breed (:breed-value static))
  (db/insert-pre! :pact (:pact-value static))
  (db/insert-pre! :strategy (:strategy-value static))
  (db/insert-pre! :ticker (:ticker-value static))
  (db/insert-pre! :currency (:currency-value static)))

(defn set-dynamic! []
  (db/execute! (:client-schema dynamic))
  (db/execute! (:person-schema dynamic))
  (db/execute! (:category-schema dynamic))
  (db/execute! (:account-schema dynamic))
  (db/execute! (:matrix-schema dynamic))
  (db/execute! (:quote-schema dynamic))
  (db/execute! (:roll-schema dynamic))
  (db/execute! (:deal-schema dynamic))
  (db/execute! (:leg-schema dynamic))
  (db/execute! (:proj-schema dynamic))
  (db/execute! (:tran-schema dynamic))
  (db/execute! (:journal-schema dynamic)))







;;; MOCKING
(defn set-mock! []
  (let [data
        [{:name "Ease" :class "A"}
         {:name "Hedge" :class "A"}
         {:name "Specuation" :class "A"}
         {:name "America" :class "B"}
         {:name "Europe" :class "B"}
         {:name "Asia" :class "B"}
         {:name "Rider" :class "C"}
         {:name "Non-Rider" :class "C"}
         {:name "Biker" :class "C"}
         {:name "Rock" :class "D"}
         {:name "Hiphop" :class "D"}
         {:name "Classic" :class "D"}
         {:name "Basketball" :class "E"}
         {:name "Football" :class "E"}
         {:name "Golf" :class "E"}
         {:name "Violin" :class "F"}
         {:name "Piano" :class "F"}
         {:name "Flute" :class "F"}
         {:name "Math" :class "G"}
         {:name "Science" :class "G"}
         {:name "English" :class "G"}]]
    (db/insert! :category data)))
