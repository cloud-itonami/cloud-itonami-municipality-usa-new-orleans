(ns culture.facts
  "Regional-culture catalog for New Orleans -- local dishes, beverages,
  festivals and heritage sites, piggybacked onto this municipality
  compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"new-orleans"
   [{:culture/id "new-orleans.dish.gumbo"
     :culture/name "Gumbo"
     :culture/municipality "new-orleans"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Heavily seasoned Louisiana stew of stock, meat or seafood, the 'holy trinity' of vegetables and a thickener, served over rice; the New Orleans variation is Louisiana's official state cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Gumbo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-orleans.dish.jambalaya"
     :culture/name "Jambalaya"
     :culture/municipality "new-orleans"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Savory one-pot rice dish of meat and/or seafood with vegetables and spices, developed in Louisiana from African, Spanish and French influences."
     :culture/url "https://en.wikipedia.org/wiki/Jambalaya"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-orleans.dish.po-boy"
     :culture/name "Po' boy"
     :culture/municipality "new-orleans"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Sandwich originally from New Orleans, traditionally filled with roast beef, ham or fried seafood on crisp-crusted French bread."
     :culture/url "https://en.wikipedia.org/wiki/Po%27_boy"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-orleans.dish.beignet"
     :culture/name "Beignet"
     :culture/municipality "new-orleans"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Deep-fried pastry dusted with powdered sugar, brought to New Orleans in the 18th century by French colonists and part of home-style Creole cooking; Louisiana's official state doughnut."
     :culture/url "https://en.wikipedia.org/wiki/Beignet"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-orleans.dish.muffuletta"
     :culture/name "Muffuletta"
     :culture/municipality "new-orleans"
     :culture/country "USA"
     :culture/kind :dish
     :culture/summary "Sandwich on round Sicilian sesame bread layered with marinated olive salad, cured meats and cheeses; created in 1906 at a New Orleans grocery."
     :culture/url "https://en.wikipedia.org/wiki/Muffuletta"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-orleans.beverage.sazerac"
     :culture/name "Sazerac"
     :culture/municipality "new-orleans"
     :culture/country "USA"
     :culture/kind :beverage
     :culture/summary "Cognac- or whiskey-based cocktail with absinthe, bitters and sugar, originally from New Orleans; designated the city's official cocktail in 2008."
     :culture/url "https://en.wikipedia.org/wiki/Sazerac"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-orleans.festival.mardi-gras"
     :culture/name "Mardi Gras in New Orleans"
     :culture/municipality "new-orleans"
     :culture/country "USA"
     :culture/kind :festival
     :culture/summary "Annual carnival season culminating on Fat Tuesday, featuring parades organized by krewes who throw beads and trinkets to the crowds."
     :culture/url "https://en.wikipedia.org/wiki/Mardi_Gras_in_New_Orleans"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-orleans.festival.jazz-heritage-festival"
     :culture/name "New Orleans Jazz & Heritage Festival"
     :culture/municipality "new-orleans"
     :culture/country "USA"
     :culture/kind :festival
     :culture/summary "Annual festival held at the Fair Grounds Race Course since 1970, showcasing local music, food and cultural traditions."
     :culture/url "https://en.wikipedia.org/wiki/New_Orleans_Jazz_%26_Heritage_Festival"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-orleans.heritage.french-quarter"
     :culture/name "French Quarter"
     :culture/name-local "Vieux Carré"
     :culture/municipality "new-orleans"
     :culture/country "USA"
     :culture/kind :heritage
     :culture/summary "The oldest neighborhood in New Orleans, developed after the city's 1718 founding; designated a National Historic Landmark district in 1965."
     :culture/url "https://en.wikipedia.org/wiki/French_Quarter"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-usa-new-orleans culture catalog "
                 "(ADR-2607171400): " (count (get catalog "new-orleans"))
                 " New Orleans entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
