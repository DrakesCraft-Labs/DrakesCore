# DrakesCraft Suites

Destino de consolidacion: los repositorios historicos siguen siendo la fuente de verdad hasta que cada migracion pase compatibilidad y se publique.

| Suite | Responsabilidad | Runtime |
| --- | --- | --- |
| Core | API, identidad, configuracion y compatibilidad | Java/Paper |
| Tech | maquinas y automatizacion | Java/Paper |
| Bio | cultivos, alimentos y alquimia | Java/Paper |
| Magic | dioses, reliquias y progresion | Java/Paper |
| Generators | generacion y combustibles | Java + Rust bridge |
| Utility | almacenamiento y logistica | Java + Rust bridge |
| Combat | equipo, mobs, jefes y PvE | Java/Paper |
| Server | gameplay, administracion e integraciones | Java/Paper |

Los identificadores existentes se conservan. Rust se usa solo en lotes versionados, con fallback Java, para grafos de redes, energia y almacenamiento.
