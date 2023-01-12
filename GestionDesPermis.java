import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class GestionDesPermis {

		private static ArrayList<Permis> listepermis;


		public GestionDesPermis(){
			listepermis = new ArrayList<Permis>();
			chargerPermis();
		}


	public void chargerPermis() {
		listepermis = chargerFichier();
	}

	
		public String toString() {

		String moto = "";
		String voiture = "";
		String motoVoiture = "";
		
		PermisVoiture pv = null;
		PermisMoto pm = null;
		
		int position = 0;
		
		for (Permis permis : getListePermis()) {

			position++;

			if (permis instanceof PermisVoiture) {
				pv = (PermisVoiture) permis;
				voiture = voiture + position +" - "+pv.toString()+"\n";
			}

			else if(permis instanceof PermisMoto) {
				pm = (PermisMoto) permis;
				moto = moto + position +" - "+pm.toString()+"\n";
			}
			
		}
		
		motoVoiture = "Voici ci-dessous l'ensemble de tous les permis voiture : \n\n"+voiture+"\n\n"
				+"\nVoici ci-dessous l'ensemble de tous les permis moto : \n\n"+moto+"\n\n";

		return motoVoiture;
	}
	
	
	
	public ArrayList<Permis> chargerFichier() {
		ArrayList<Permis> listeperm = null;
		try {
			FileInputStream fis = new FileInputStream("/Users/yanice/Desktop/PROJET_JAVA_BOUZIANE_YANICE_SAMI_COSTA/Permis.txt");
			
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			listeperm = (ArrayList<Permis>)ois.readObject();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listeperm;
	}
	
	
	public void listeInitialiser() {
		ArrayList<Permis> listeperm = new ArrayList<Permis>();
		setListePermis(listeperm);
		try {
			FileOutputStream fos = new FileOutputStream("/Users/yanice/Desktop/PROJET_JAVA_BOUZIANE_YANICE_SAMI_COSTA/Permis.txt");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(listeperm);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void AjouterPermis(Permis permis) throws ExceptionPermis {

		ArrayList<Permis> listep = getListePermis();
		ArrayList<Integer> idP = new ArrayList<Integer>();

		for(Permis p1 : listep)
		{
	  		idP.add(p1.getIdPermis());
		}

		if(idP.contains(permis.getIdPermis()))
	  	{
	  			throw new ExceptionPermis();
	  	}
	  	else{
	  		listep.add(permis);
			setListePermis(listep);
			MajSerialisation();
	  	}
		
	}

	
	public void supprimerPermis(int position) {
		ArrayList<Permis> listeperm = getListePermis();
		position = position - 1;
		if(position >= listeperm.size()){
			  System.out.println("Erreur sur la saisie de la position");
			}else{
				listeperm.remove(position);
				setListePermis(listeperm);
				MajSerialisation();
			}
	}
	
	
	public void MajSerialisation() {
		try {
			FileOutputStream fos = new FileOutputStream("/Users/yanice/Desktop/PROJET_JAVA_BOUZIANE_YANICE_SAMI_COSTA/Permis.txt");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(getListePermis());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Permis> getListePermis() {
		return listepermis;
	}

	public void setListePermis(ArrayList<Permis> listeperm) {
		listepermis = listeperm;
	}


	public static Permis CreerPermis(Scanner sc, Conducteur conducteur, Assurance assurance) 
  {
       System.out.println("Vous allez créer un Permis, veuillez saisir les informations suivantes :\n");
       Permis permis = null;
       String FORMAT_DE_LA_DATE = "dd/MM/yyyy";
       Date dateExp = new Date();


       System.out.println("\nVeuillez saisir le numero de permis : ");
       String idSaisie = sc.nextLine();
       int idPermis = Integer.parseInt(idSaisie);


       System.out.println("\nVeuillez saisir l'"+"état de votre permis (true si ACTIF, false si SUSPENDUS) : ");
       String etat = sc.nextLine(); 
       Etat etatpermis = null;
       if(etat == "true")
       {
          etatpermis = Etat.ACTIF;
       }
       else
       {
          etatpermis = Etat.SUSPENDUS;
       }


       System.out.println("\nSouhaitez-vous ajouter un permis : \n- voiture (V) \n- moto(M)");
       String typePermis = sc.nextLine();

      switch (typePermis) {
        case "M":
          System.out.println("\nAvez-vous obtenu le bsr ? (Oui ou Non) : ");
          String saisie = sc.nextLine();
          boolean bsr = false;

          if(saisie == "oui")
          {
            bsr = true;
          }

          permis = new PermisMoto(idPermis, dateExp, etatpermis, conducteur, assurance, bsr);
          break;

        case "V":

          try{
              Calendar calendar = Calendar.getInstance();
              calendar.setTime(dateExp);
              calendar.add(Calendar.YEAR,10);
              dateExp = calendar.getTime() ;

              System.out.println("\nVeuillez saisir la date d'obtention (jj/mm/aaaa) : ");
              String dateOb = sc.nextLine();
              DateFormat df1 = new SimpleDateFormat(FORMAT_DE_LA_DATE);
              df1.setLenient(false);
              df1.parse(dateOb);
              String date1[] = dateOb.split("/");
              Date dateObt = date(Integer.parseInt(date1[2]), Integer.parseInt(date1[1]), Integer.parseInt(date1[0]));
          	  
          	  permis = new PermisVoiture(idPermis, dateExp, etatpermis, conducteur, assurance, dateObt);}
          
           catch (ParseException e) {
               System.out.println("Format Date Incorrect.\n");
           }
           break;
        
        default:
          break;
      }
     
     return permis;
  }

		public static Conducteur CreerConducteur(Scanner sc, Assurance assurance) {

		String FORMAT_DE_LA_DATE = "dd/MM/yyyy";
		Conducteur conducteur = null;
		System.out.println("Vous allez créer un Conducteur, veuillez saisir les informations suivantes :\n");
	 	
    	   
    	   
    	   
    	   try {
               
    	   		System.out.println("\nVeuillez saisir votre nom : ");
                String nom = sc.nextLine();

    	   		System.out.println("\nVeuillez saisir votre prénom : ");
                String prenom = sc.nextLine();

                System.out.println("\nVeuillez saisir la ville de naissance : ");
                String ville = sc.nextLine();

                System.out.println("\nVeuillez saisir la date de naissance (jj/mm/aaaa) : ");
           		String date = sc.nextLine();
 	 	   		DateFormat df = new SimpleDateFormat(FORMAT_DE_LA_DATE);
           		df.setLenient(false);
           		df.parse(date);
           		String dateD[] = date.split("/");
           		Date ddn = date(Integer.parseInt(dateD[2]), Integer.parseInt(dateD[1]), Integer.parseInt(dateD[0]));
               
               
        	   conducteur = new Conducteur(nom, prenom, ddn, ville);

           } catch (ParseException e) {
               System.out.println("Format Date Incorrect.\n");
           }
		return conducteur;
	}

	public static Date date(int jj, int mm, int aaaa) {
		Date date = new GregorianCalendar(aaaa, mm - 1, jj).getTime();
		return date;
	}

	public static Assurance CreerAssurance(Scanner sc) {
	
	   String FORMAT_DE_LA_DATE = "dd/MM/yyyy";
	   Assurance assurance = null;
 	   System.out.println("Vous allez créer un assurance, veuillez saisir les informations suivantes :\n");
 	   
 	  
 	  try {

 	  	   System.out.println("\nVeuillez saisir le nom de votre assurance : ");
 	 	   String assuranceSaisie = sc.nextLine();	

 	 	   System.out.println("\nVeuillez saisir le numero de contrat : ");
 	  	   String idSaisie = sc.nextLine();
 		   int idContrat = Integer.parseInt(idSaisie);

 	 	   System.out.println("\nVeuillez saisir la date de souscription (jj/mm/aaaa) : ");
 	 	   String date = sc.nextLine();
 	 	   DateFormat df = new SimpleDateFormat(FORMAT_DE_LA_DATE);
           df.setLenient(false);
           df.parse(date);
           String dateD[] = date.split("/");
           Date dateSous = date(Integer.parseInt(dateD[2]), Integer.parseInt(dateD[1]), Integer.parseInt(dateD[0]));

 	  	   System.out.println("\nVeuillez saisir le prix (la , doit être remplacer par un . Ex : 10,50 --> 10.50): ");
 	  	   String prixSaisie = sc.nextLine();
 		   double prix = Double.parseDouble(prixSaisie);
 		   
 	 	   assurance = new Assurance(assuranceSaisie,prix,dateSous,idContrat);


        }catch(NumberFormatException e) {
            System.out.println("Le format du prix n'est pas respecté");
        }catch (ParseException e) {
               System.out.println("Format Date Incorrect.\n");
           }
 	   
 	   return assurance;
	}

	public static void menu(){

	System.out.println("(ls) Afficher la liste des permis de conduire\n");
	System.out.println("(ap) Ajouter un permis de conduire\n");
	System.out.println("(sp) Supprimer un permis de conduire\n");
	System.out.println("(c) Changer la police d'assurance d'un conducteur\n");
	System.out.println("(spc) Suspendre un permis de conduire\n");
	System.out.println("(r) Renouveler un permis de conduire\n");
	System.out.println("(av) Ajouter un véhicule à un permis de conduire\n");
	System.out.println("(rv) Retirer un véhicule d'un permis de conduire\n");
	System.out.println("(q) Quitter l'application\n");
	System.out.println("(a) Afficher le manuel d'aide\n");
}

public static void main(String[] args) {

		GestionDesPermis check = new GestionDesPermis();
		Scanner sc = new Scanner(System.in);
		boolean on = true;

		
		System.out.println("Bienvenue sur votre application de Gestion de permis\n\n");

		System.out.println("Quelle action souhaitez-vous effectuer ? \n");
		menu();

		while(on)
		{

			String action = sc.nextLine();
			
			switch(action){
				case "ls":
		    	   System.out.println(check);
		    	   System.out.println("Quelle action souhaitez-vous effectuer ? \n");
				   menu();
		           break;
		   
		       case "ap":
		    	   Assurance assurance = CreerAssurance(sc);
		    	   if(assurance == null) {
		    	   	   //throw new NullPointerException(" Probléme à la création de l'assurance \n");
		    		   break;
		    	   }
		    	   System.out.println("\n CHECK 1 \n");
		    	   Conducteur conducteur = CreerConducteur(sc, assurance);
		    	   if(conducteur == null) {
		    	   	   //throw new NullPointerException(" Probléme à la création du conducteur \n");
		    		   break;
		    	   }
		    	   System.out.println("\n CHECK 2 \n");
		    	   Permis permis = CreerPermis(sc, conducteur,assurance);
		    	   if(permis == null) {
		    	   	   //throw new NullPointerException(" Probléme à la création du permis \n");
		    		   break;
		    	   }
		    	   System.out.println("\n CHECK 3 \n");
					try {
						check.AjouterPermis(permis);
						System.out.println("\n CHECK 4 \n");
						System.out.println("\nLe permis à été ajouter à la liste avec succés !!!!!");
					} catch (ExceptionPermis e) {
						System.out.println(e.toString());
					}

					System.out.println("Quelle action souhaitez-vous effectuer ? \n");
					menu();
				   break;
		          
		       case "sp":
		           System.out.println("\nDans la liste ci-dessous veuillez choisir l'ID du permis à supprimer");
		    	   System.out.println(check);
		    	   String id = sc.nextLine();
		    	   int idContrat = Integer.parseInt(id);
		    	   boolean val = false;
		    	   int position = 0;
		    	   int i = 0;
		    	   
		    	   for(Permis p1 : listepermis)
					{
				  		if(p1.getIdPermis() == idContrat)
				  		{
				  			System.out.println("\nLe permis à été trouvé, il va être supprimer\n");
				  			val = true;
				  			position = i;
				  		}
				  		i++;
					}

		    	   if(val == true) {
		    		   check.supprimerPermis(position);
		    		   System.out.println("\nLe permis à été supprimer\n");
		    	   }else {
		    		   System.out.println("\n Id non valide!\n");
		    	   }
		    	   System.out.println("Quelle action souhaitez-vous effectuer ? \n");
					menu();
		           break;
		       
		       case "c":
		       		System.out.println("\n Saisir la position du conducteur dont l'assurance doit être modifier : \n");
		       		String s = sc.nextLine();
		       		int pos = Integer.parseInt(s);


		       		if(pos >= listepermis.size())
		    	    {
		    	    	pos = pos - 1;
		    	    	Permis mips = listepermis.get(pos);
			       		Assurance assur = CreerAssurance(sc);
	 			    	mips.setAssurance(assur);
	 			    	listepermis.set(pos, mips);
	 			    	check.MajSerialisation();
			    	    System.out.println("\n Assurance modifié !!!! \n");
		    	    }
		    	    else{
		    	    	System.out.println("\n"+ listepermis.size() +"\n");
		    	    	System.out.println("\n Position non valide \n");
		    	    }

		       		

		    	   System.out.println("Quelle action souhaitez-vous effectuer ? \n");
				   menu();
		           break;
		       
		       case "spc":
		       		System.out.println("\n Saisir la position du du permis à Suspendre : \n");
		       		String posSup = sc.nextLine();
		    	    int posi = Integer.parseInt(posSup);
		    	    
		    	    if(posi >= listepermis.size())
		    	    {
		    	    	posi = posi - 1;
		    	    	Permis prm = listepermis.get(posi);
			    	    prm.Suspendre();
			    	    listepermis.set(posi, prm);
			    	    check.MajSerialisation();
			    	    System.out.println("\n Le permis à été suspendus \n");
		    	    }
		    	    else{
		    	    	System.out.println("\n Position non valide \n");
		    	    }
		  			
		  	       System.out.println("Quelle action souhaitez-vous effectuer ? \n");
				   menu();	
		           break;
		       
		       case "r":
		    	    System.out.println("\n Saisir la position du du permis à Renouveler : \n");
		       		String pup = sc.nextLine();
		    	    int pst = Integer.parseInt(pup);
		    	    
		    	    if(pst >= listepermis.size())
		    	    {
		    	    	pst = pst - 1;
		    	    	Permis perm = listepermis.get(pst);
			    	    perm.Renouvellement(perm.getExpiration());
			    	    perm.setStatus(Etat.ACTIF);
			    	    listepermis.set(pst, perm);
			    	    check.MajSerialisation();
			    	    System.out.println("\n Le permis à été Renouveler !!!!! \n");
		    	    }
		    	    else{
		    	    	System.out.println("\n Position non valide \n");
		    	    }
		    	    System.out.println("Quelle action souhaitez-vous effectuer ? \n");
				    menu();
		           break;


		        case "av":

		           ArrayList<Vehicule> vhc = new ArrayList<Vehicule>(); 
		           Permis ppermis = null;
		    	   System.out.println("Nous allons ajouter un véhicule à la liste\n");

		    	   System.out.println("Veuillez commencer par choisir un permis par sa position : \n");
		    	   String pp = sc.nextLine();
		    	   int posper = Integer.parseInt(pp);


		    	   if(posper >= listepermis.size())
		    	    {
		    	    	posper = posper - 1;
		    	    	ppermis = listepermis.get(posper);
			    	    vhc = ppermis.getListeVehicules();
			    	    
		    	    }
		    	    else{
		    	    	System.out.println("\n Position non valide \n");
		    	    }

		    	   System.out.println("Souhaitez vous ajouter : \n");
		    	   System.out.println(" - Une moto (M)\n");
		    	   System.out.println(" - une voiture (V)\n");

		    	   String res = sc.nextLine();

		    	   switch(res)
		    	   {
		    	   		case "M":

		    	   			System.out.println("Veuillez saisir l'ID du véhicule :\n");
		    	   			String di = sc.nextLine();
		    	   			int idVehicule = Integer.parseInt(di);

		    	   			System.out.println("Veuillez saisir l'annee du véhicule :\n");
		    	   			String a = sc.nextLine();
		    	   			int annee = Integer.parseInt(a);

		    	   			System.out.println("Veuillez saisir le modele du véhicule :\n");
		    	   			String model = sc.nextLine();
		    	   			
		    	   			System.out.println("Veuillez saisir marque du véhicule :\n");
		    	   			String marque = sc.nextLine();

							System.out.println("Veuillez saisir le cylindre du véhicule (entre 50 et 1200):\n");
		    	   			String c = sc.nextLine();
		    	   			int cc = Integer.parseInt(c);		

		    	   			System.out.println("Veuillez saisir la couleur du véhicule:\n");
		    	   			String couleur = sc.nextLine();

		    	   			System.out.println("Veuillez saisir le nombre de roue du véhicule :\n");
		    	   			String nb = sc.nextLine();
		    	   			int nbroue = Integer.parseInt(nb);	

		    	   			Moto mm = new Moto(idVehicule,annee,model,marque,cc,couleur,nbroue);

		    	   			ppermis.AjouterVehicule(mm);
		    	   			listepermis.set(posper,ppermis);
		    	   			check.MajSerialisation();

		    	   			System.out.println("\n La moto à été ajouté !!!!!!! \n");

		    	   			break;


		    	   		case "V":
		    	   			System.out.println("Veuillez saisir l'ID du véhicule :\n");
		    	   			String id1 = sc.nextLine();
		    	   			int idVeh = Integer.parseInt(id1);

		    	   			System.out.println("Veuillez saisir l'annee du véhicule :\n");
		    	   			String abc = sc.nextLine();
		    	   			int an = Integer.parseInt(abc);

		    	   			System.out.println("Veuillez saisir le modele du véhicule :\n");
		    	   			String modele = sc.nextLine();
		    	   			
		    	   			System.out.println("Veuillez saisir marque du véhicule :\n");
		    	   			String mark = sc.nextLine();

							System.out.println("Veuillez saisir le nombre de place du véhicule :\n");
		    	   			String pl = sc.nextLine();
		    	   			int nbplace = Integer.parseInt(pl);		
 							
 							System.out.println("Veuillez saisir carburant du véhicule (DIESEL,ESSENCE,ETHANOL):\n");
		    	   			String ca = sc.nextLine();
		    	   			Carburant carburant = Carburant.DIESEL; // Valeur par défaut

		    	   			if(ca.equals("DIESEL"))
		    	   			{
		    	   				carburant = Carburant.DIESEL;
		    	   			}

		    	   			if(ca.equals("ESSENCE"))
		    	   			{
		    	   				carburant = Carburant.ESSENCE;
		    	   			}

		    	   			if(ca.equals("ETHANOL"))
		    	   			{
		    	   				carburant = Carburant.ETHANOL;
		    	   			}

		    	   			Voiture vv = new Voiture(idVeh,an,modele,mark,nbplace,carburant );

		    	   			ppermis.AjouterVehicule(vv);
		    	   			listepermis.set(posper,ppermis);
		    	   			check.MajSerialisation();

		    	   			System.out.println("\n La voiture à été ajouté !!!!!!! \n");
		    	   			break;
		    	   }
		    	   System.out.println("Quelle action souhaitez-vous effectuer ? \n");
				   menu();
		           break;


		        case "rv":

		           Permis permis1 = null;
		           System.out.println("Veuillez choisir la position du permis dont vous souhaitez supprimer un vehicule : \n");

		           String supVeh = sc.nextLine();
		    	   int pos1 = Integer.parseInt(supVeh);


		    	   if(pos1 >= listepermis.size())
		    	    {
		    	    	pos1 = pos1 - 1;
		    	    	permis1 = listepermis.get(pos1);
			    	    
		    	    }
		    	    else{
		    	    	System.out.println("\n Position non valide \n");
		    	    }



		    	   System.out.println("Veuillez choisir ci-dessous l'indice du véhicule à supprimer : \n");

		    	   if(permis1.getListeVehicules().size() > 0)
		    	   {
		    	   		System.out.println(permis1.AfficherVehicules());
		    	   		String ind = sc.nextLine();
		    	   		int idSupp = Integer.parseInt(ind);
		    	   		idSupp = idSupp - 1;
		    	   		permis1.SupprimerVehicule(idSupp);
		    	   		listepermis.set(idSupp,permis1);
		    	   		check.MajSerialisation();
		    	   		System.out.println("\n Le vehicule à été supprimer \n");
		    	   }
		    	   else
		    	   {
		    	   	System.out.println("\nLa liste des vehicules est vide !!!!\n");
		    	   }
		    	   System.out.println("Quelle action souhaitez-vous effectuer ? \n");
				   menu();
		           break;

		       case "a":
		    	   menu();
		           break;

		       case "q":
		    	   on = false;
		           break;
		           
		       default:
		    	   break;
			}
		}
		
		System.out.println("\nAu revoir et faite attention à vous sur la route !");
		
	}
	
}