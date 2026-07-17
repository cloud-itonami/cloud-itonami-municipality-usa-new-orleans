(ns ordinance.facts
  "Municipal-ordinance compliance catalog for New Orleans (City of New
  Orleans) -- a Wave 1b addition per ADR-2607171400 addendum 2, joining
  the cloud-itonami-municipality-* compliance-fact family of
  ADR-2607141700 (cloud-itonami-compliance-fact-federation; see
  cloud-itonami-municipality-usa-washington-dc for the US sibling).

  Every entry cites an OFFICIAL City of New Orleans URL (czo.nola.gov,
  the city's official codification of its Comprehensive Zoning
  Ordinance) -- never fabricated. An ordinance not in this table has NO
  spec-basis, full stop; extend `catalog`, do not invent an
  id/url/number.

  Both entries below were verified on 2026-07-17. The Comprehensive
  Zoning Ordinance entry cites the city's official CZO codification site
  (czo.nola.gov, 'A Codification of the Zoning Ordinances of New
  Orleans, Louisiana', read live); its M.C.S. designation ('the
  Comprehensive Zoning Ordinance, Ordinance No. 4,264 M.C.S., as
  amended') was read verbatim from an official City Planning Commission
  public hearing notice PDF downloaded from nola.gov and read via the
  Read tool. The interim-zoning-district entry was read live on the
  cited czo.nola.gov page, which prints its establishing and extending
  ordinance numbers and dates (Ord. 29,701 M.C.S. of 11-7-23, extended
  by Ord. 30,130 M.C.S. of 11-7-24 and Ord. 30,292 M.C.S. of 4-21-25)
  and states that the district expired on November 5, 2025 (kept here
  as reference/archive data). Council/CPC documents read during
  verification print office-holders' names; those were read only to
  locate citations and are NOT stored anywhere in this catalog,
  consistent with this family's no-personal-names discipline.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"new-orleans"
   [{:ordinance/id "new-orleans.comprehensive-zoning-ordinance"
     :ordinance/title "Comprehensive Zoning Ordinance of the City of New Orleans"
     :ordinance/municipality "new-orleans"
     :ordinance/country "USA"
     :ordinance/kind :ordinance
     :ordinance/number "Ordinance No. 4,264 M.C.S., as amended (per City Planning Commission public hearing notices)"
     :ordinance/url "https://czo.nola.gov/"
     :ordinance/url-provenance :official-czo-nola-gov
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:zoning :urban-planning}}
    {:ordinance/id "new-orleans.commercial-str-interim-zoning-district-2023"
     :ordinance/title "Commercial Short-Term Rental Interim Zoning District (Comprehensive Zoning Ordinance, Article 19)"
     :ordinance/municipality "new-orleans"
     :ordinance/country "USA"
     :ordinance/kind :ordinance
     :ordinance/number "Ord. 29,701 M.C.S. (2023-11-07); extended by Ord. 30,130 M.C.S. (2024-11-07) and Ord. 30,292 M.C.S. (2025-04-21); district expired 2025-11-05"
     :ordinance/url "https://czo.nola.gov/print.aspx?printpath=%2FArticle-19%2F19-4%2F19-4-A%2F19-4-A-20&classname=nola.ordinance"
     :ordinance/url-provenance :official-czo-nola-gov
     :ordinance/enacted-date "2023-11-07"
     :ordinance/last-revised-date "2025-04-21"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:housing :tourism :zoning}}]})

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
      :note (str "cloud-itonami-municipality-usa-new-orleans Wave 1b (ADR-2607171400 "
                 "addendum 2 / family ADR-2607141700): "
                 (count (get catalog "new-orleans")) " New Orleans entries seeded "
                 "with official czo.nola.gov citations. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
