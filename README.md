# MindustryCryogenesis
A mod for Mindustry adding a third, frozen planet.

## Roadmap

Cryogenesis v[MAJOR].[MINOR].[PATCH] - [PHASE]

Major version is updated for major overall gameplay additions or changes.<br>
Minor version is upated for large content additions or changes.<br>
Patch is updated for small additions or changes to specific content.<br>
Phase denotes the phase of development the version is in:

- Alpha versions are versions created prior to the v1.0.0 release.
- Beta versions are pre-releases of an upcoming major update.
- Release versions are the initial, official releases of major updates.
- Versions without a phase are updates to previous releases.

v0.0.0 - Initial commits and .hjson tests

v0.1.0 - Preliminary .java testing

v0.6.0 - Planet addition

v0.10.0 - Start adding Campaign maps

v1.0.0 - Initial release of Cryogenesis. Mod will be playable, but probably won't include higher tier content.

v?.?.? - Expansion of the campaign up to T5.

FUTURE - Further content revamps and additions

## Version History

v0.0.0 - Initial commits and .hjson tests
v0.1.0 - Preliminary .java testing
v0.2.0 - Pipe dual transport functionality
v0.3.0 - Pipe liquid effects
v0.4.0 - Non-core unit tests
v0.5.0 - Nickel item and blocks
v0.6.0 - Planet, tech tree, and placeholder sector
v0.7.0 - Scrapping mechanic
v0.8.0 - Environment blocks
v0.9.0 - Sprite overhauls
v0.10.0 - Early campaign development

## FAQ

#### Q: More additions after T5? Does this mean we get T6?
A: Not unless vanilla gets them first! Those revamps and additions are to polish the mod and bring it up to the other two campaigns in terms of quality.
<sub><sup>Or maybe for if I come up with a really cool idea that I have to add<sup><sub>

#### Q: Did you make this FAQ just so you could answer the previous question?
A: Yep.

## Upcoming Features

### Legend

- Uncompleted item
- ~~Completed item~~

### To-Do List

- ~~Pipe functions as a conduit too~~
	- ~~Replace Pipe and variant costs with Iron instead of scrap [FUTURE]~~
	- ~~Fix Pipe liquid randomness~~
	- Pipe variants
		- Armored Pipe accent color
	- ~~Pipe Unloader~~
		- ~~Pipe Unloader speed to highest boosted Pipe speed~~
		- Resprite Pipe Unloader [FUTURE]
		- ~~Ground unit~~
			- ~~Possibly upgrade T1 Eluma to T2 Schizi and create new unit for T1~~
			- Replace Schizi with new T2 armed with turrets
			- ~~Fix Schizi treads~~
			- Fix Emula treads
		- ~~Large Nickel Wall~~
		- ~~Basic turret~~
			- ~~Playable map~~
				- ~~Planet~~
					- ~~Tech Tree~~
- ~~Buff Pipe with certain liquid~~
	- Same for variants
	- Other liquid boost values (for mixtech)
	- Liquid booster tooltips
- ~~Environment Blocks~~
	- ~~Nickel Ore~~
		- ~~Revamp Zero progression/tutorial~~
	- First campaign map
- Better mod icon and description
	- Polish README
- Handle team icon internally
	- Team icon outline
- Devastate turret [FAR FUTURE]
- ~~Fix UI icons~~
	- ~~Fix Core: Thread UI icon~~
	- ~~Redo UI icons with 6px stroke~~
- ~~Build Turrets on core units, may require resprite [FUTURE]~~
- Core: Hub [FAR FUTURE]
	- Iono unit [FAR FUTURE]
	- Core: Nexus [FAR FUTURE]
		- Exo unit [FAR FUTURE]
- Resprite Nickel Compactor to look less like camouflage [FUTURE]
- Giant Nickel Wall [FUTURE, TBD]
- ~~Remove/hide depricated content~~
- Recolor Innelis meshes
- ~~Fix missing in-text icons~~
- ~~Auger buildings~~
- ~~Scrapper block~~
	- ~~Sprite~~
	- ~~Don't attach to power lines~~
	- Decals
- ~~Scrapper AI~~
	- Don't target same unit [TBD]
	- Prioritize largest scrap [FUTURE]
	- ~~Filter and select best scrapper~~
	- ~~Scavenge command~~
- ~~Fix ScrapAI team swap logic~~
- Wind turbine buildings [FUTURE]
- ~~Scrap spawner weapon position~~
	- Slight location randomization [TBD]
- ~~Prevent Demise scrap units from despawning on sector capture~~
- Campaign difficulty settings [FAR FUTURE]
- ~~Fix map view cloud alpha zoom weirdness~~
- Add attributes for environment blocks (for mixtech)
- Improve hot springs liquid shader
- Auger background uses floor color?

#### Sprites
- Eluma turret too thin?
- Greeble Meso, change cell
- Resprite Nickel Compactor
- Re-export all sprites with antialias of 1 (DONE: blocks, environment)
- Fix Boiling status effect sprite
- ~~Sluice liquid sprite~~
- ~~Steam sprite~~ (Might need fixing)
- Change Sluice color, currently too close to cryofluid
- Fix crispy pelt ui icon

### Wishlist:

Tunnel Pipe doesn't need end - push/pull from anything connected<br>
OR<br>
Tunnel Pipe directional sprites, looks similar to Pipe

- Pixel-perfect Steam icon, better Sluice icon antialiasing

- Slag-filled Pipes and variants incinerate items
- Buff Pipe item capacity
- Fix missing nearby pipe caps when connecting to blendbits == 0

- Demise team in addition to vanilla teams; don't replace green

### Wiggle's Accomplishments List

- Animate Pipe liquid draw area
- Pipe connects to liquid outputs
- Sluice tile hover tooltip

### Brainstorming

#### Basic resource progression

Scrap -> Nickel -> Titanium -> Elastanium -> Aluminum -> Cryosteel

Sand + Scrap -> Silicon (Scrap is assumed to be some combination of all Innelis resources, including Al, and 3 SiO2 + 4 Al = 3 Si + Al2O3)

Sand -> Insulation (Aerogel, factory built with Ti/Elastanium?)

#### Sector progression

Zero (Cryogenesis) - Mine Nickel -> Research/build Scrapper -> Destroy derelict units -> Scrap units -> Research/build Nickel Compactor -> Research/build Pipes -> Scrap more units -> Research/build Pelt and Nickel Wall -> Enemy attacks -> Defeat all waves -> [TBD: Power?] Research/build Powderizer and Silicon Furnace -> Research/build Auger -> Unlocks next sector
[TBD] - Set up Silicon production -> Research/build T1 air factory -> Research/produce Vice -> Enemy attacks -> ???

#### PlanetGenerator

Should only need to define isEmissive (for cool bases), getHeight, getColor, and getEmissiveColor (for cool bases)

#### Biomes

- Cryo Ice 
- Cryo Snow
- Geothermal
- Cliffs
- Lowlands/Wastes/Dunes
- Shores

Mini-biomes:
- ~~Laterite deposits~~ (Deprecated)
- ~~Magma Stone hotspots~~ (Deprecated)
- Stellarite
- ~~Hot Springs~~

#### Tiles

- ~~Cryo Ice bubbles instead of partial streaks, greenish tint?~~ (Not added)
- ~~Magamatic Stone tint changes, flow-style formation~~ (Deprecated)
- ~~Laterite used in shores biome? Found IRL with "high temperatures and heavy rainfall/dry periods" (https://en.wikipedia.org/wiki/Laterite)~~ (Deprecated)
- ~~Sulfur and Travertine in Hot Springs~~
- ~~[TBD] liquid (Soda/Sluice) in hot springs, inflicts Boiling. Very low viscosity, even less than water.~~
- Stellarite, with Stellarite Shard props

#### Other

- Basic scrapper only produces scrap, higher tier scrappers can reclaim other resources? (Replace reqs[i].item with items.scrap on line 169 of PayloadDeconstructor? Would require override or new class)

- "last Stand"-style sectors where no resources can be deposited into the core
	- Long time before first wave, but do NOT wait until player is ready (this would allow pseudo-infinite container stockpiling exploits)
	- Core spawns in walls to prevent blocks depositing
		- Disable core schematics to prevent overriding walls/importing items that way somehow? (allowLaunchSchematics flag may do this)
		- Disable units depositing items into the core somehow OR remove core unit item cap on these levels ONLY WORKS if Innelis has no logic (overriding allowDeposit may do this, ensure it ONLY affects last stand maps)

- "Puncture" early anti-air turret
- "Bore" midgame single target laser turret, inflicts melting
- Parallax counterpart, pushes air targets away (Vertex?)
- "Pelt" basic turret NAME WIP
- "Barrage" upgrade to Pelt (Possibly rename, term is used frequently in description text)
- "Avenge" upgrade to Barrage

- Boiling status effect inflicted by the liquid in hot springs, effects TBD. Probably just damage over time.

- Power Tower with multiple connections like a Serpulo power node. Expensive, later-game, but convenient. Heat transfer TBD.

- Induction Heater (Electric Heater equivalent)
- Hydrothermal Heater (Produces heat from hot spring liquid)

- Wind Generator (1x1), Large Wind Generator (3x3), Advanced Wind Generator (2x2, better space efficiency, can be placed offshore), Vortex Reactor? (4x4, May require advanced inputs to run)
- Some sort of extremely powerful generator/factory that requires a synthetic gaseous lifeform (Stellarite) as [TBD]. Explodes extremely violently if [TBD].

Since infrastructure on Innelis does not mostly depend on ore locations, maybe attribute tiles could play a similar role to drive building locations, such as
- Upgraded scrappers (liquefier?) gain speed bonuses when on hot tiles
- Geothermal Generator
- Other heat-buffed buildings
- Geothermal Heater (produces heat when placed on hot tiles)
- Certain tiles with a higher "wind" attribute for wind turbine buildings
	- [REALISTIC] Dune Sand high wind
	- [GAMEPLAY] Dune Stone high wind
	- [BEST?] Special Dune Sand tile instead

Cores: Terminal, Hub, Nexus

Unit lines:
- Light ground "Rollers": Eluma -> Estena -> Paxo -> Glomeris -> Eleon
- Heavy ground "Walkers": ??? -> ??? -> ??? -> ??? -> ??? -> ???
- Air assault "Floater": Fluctus -> Cavum -> Murus -> Asperitas -> Arcus
- Air support "Hoverers": Vise -> Lathe -> Gimbal -> Gyro -> Derrick
- Naval "Cruisers": ??? -> ??? -> ??? -> ??? -> ???
- Core units: Meso -> Iono -> Exo

- Assault units (everything but air support and core units) do NOT emit light. Support units emit large amounts of light, core units emit small amounts of light.
	- This way enemy 'sneak attacks' are possible, but the player can use support units to see what they're doing on attack.
	- Spotlight turret-like block and area light block?
	- Weapons fire will still emit light, so combat is visible. Plus, it looks cool.

Insulation used for water-related blocks and insulated walls (possibly power blocks and wires too?)

## Trvia
- The Lathe's sprite is a rework of the Meso's original sprite
- Scrap resources on Innelis were originally deposited directly into the core before the introduction of the scrapping system
- Cryogenesis (sector) replaced a placeholder sector named "Zero" that was used for early development
	- Attempting to play Zero will crash the game, since the map references the now-renamed Core: Thread
- Sluice was originally named "Soda" and is a mineral-rich hydrocarbon liquid rather than water
	- Similarly, Steam is not water vapor, despite its name
