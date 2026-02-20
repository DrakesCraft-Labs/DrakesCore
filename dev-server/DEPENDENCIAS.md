# Dependencias Base para DrakesCraft Sandbox

## Requeridas
1. Paper 1.20.6
2. Java 21
3. Vault
4. PlaceholderAPI
5. EssentialsX (con Economy)

## Por que se requieren
- Vault: puente de economia/servicios entre plugins.
- PlaceholderAPI: placeholders compartidos entre DrakesTab, DrakesRanks, DrakesCrates.
- EssentialsX Economy: provider de dinero para Vault.

## Opcionales recomendadas
1. ProtocolLib
2. spark

## Nota de arquitectura
La meta es evitar dependencias de gameplay externas tipo Slimefun.
Todo el gameplay tech (energia, multiblocks, armas, armaduras, guide) debe vivir en DrakesTech.
