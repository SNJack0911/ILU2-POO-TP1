package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private class Marche {
		private Etal[] etal;
		private int nbEtal;

		private Marche(int nbEtal) {
			this.nbEtal = nbEtal;
			etal = new Etal[nbEtal];

		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etal[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			for (int i = 0; i < nbEtal; i++) {
				if (etal[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			Etal[] EtalaProduit = new Etal[nbEtal];
			int x = 0;
			for (int i = 0; i < nbEtal; i++) {
				if (etal[i].contientProduit(produit)) {
					EtalaProduit[x] = this.etal[i];
					x += 1;
				}
			}
			return EtalaProduit;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < nbEtal; i++) {
				if (etal[i].getVendeur() == gaulois) {
					return etal[i];
				}
			}
			return null;
		}

		private String afficheMarche() {
			int nbEtalVide = 0;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < this.nbEtal; i++) {
				if (this.etal[i].isEtalOccupe()) {
					sb.append(etal[i].afficherEtal());

				} else {
					nbEtalVide++;
				}
				if (nbEtalVide > 0) {
					sb.append("Je reste " + nbEtalVide + "etals non utilise.\n");
				}
			}
			return sb.toString();
		}
	}

	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private int nbEtal;
	private Marche marche;

	public void Village(String nom, int nbEtal, int nbVillageoisMaximum) {
		this.nom = nom;
		marche = new Marche(nbEtal);
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}