package com.istl.dao.imp;

import com.istl.dao.PersonDAO;
import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ParsonDAOImp implements PersonDAO {

    static EntityManagerFactory emf;
    static EntityManager em;

    public ObservableList<EnrollPerson> findTopPerson(Integer value) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        ObservableList<EnrollPerson> person = FXCollections.observableArrayList();
        TypedQuery<EnrollPerson> ePerson = em.createQuery("SELECT D FROM EnrollPerson D ORDER BY D.id DESC", EnrollPerson.class);
        ePerson.setMaxResults(value);
        List<EnrollPerson> l = ePerson.getResultList();
        for (EnrollPerson p : l) {
            person.add(p);
        }

        return person;

    }

    public ObservableList<EnrollPerson> findAllPerson() {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        ObservableList<EnrollPerson> person = FXCollections.observableArrayList();
        List<EnrollPerson> ePerson = em.createQuery("SELECT e FROM EnrollPerson e ORDER BY e.id DESC").getResultList();
        for (EnrollPerson persons : ePerson) {
            person.add(persons);
        }
        return person;
    }

    public int addPerson(EnrollPerson person) {
        int result = 0;
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        if (person != null) {
            try {
                em.getTransaction().begin();
                em.persist(person);
                em.getTransaction().commit();
                result = person.getId();
            } catch (Exception e) {
            }
        }

        return result;
    }

    public int personDelete(EnrollPerson person) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        int value = 0;

        EnrollPersonBiometric biometric = new EnrollPersonBiometric();
        List<EnrollPersonBiometric> list = em.createQuery("SELECT D FROM EnrollPersonBiometric D WHERE D.personId=:personId")
                .setParameter("personId", person).getResultList();
        for (EnrollPersonBiometric bio : list) {
            biometric = bio;
        }
        em.getTransaction().begin();
        em.remove(biometric);
        em.getTransaction().commit();
        em.clear();
        if (person.getId() != null) {
            em.getTransaction().begin();
            value = em.createQuery("DELETE FROM EnrollPerson D WHERE D.id=:id")
                    .setParameter("id", person.getId()).executeUpdate();
            em.getTransaction().commit();
        }
        return value;
    }

    public ObservableList<EnrollPerson> personSearchByName(String name) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        ObservableList<EnrollPerson> list = FXCollections.observableArrayList();
        List<EnrollPerson> person = em.createQuery("SELECT D FROM EnrollPerson D WHERE D.firstNameEn LIKE :firstNameEn")
                .setParameter("firstNameEn", "%" + name + "%").getResultList();

        for (EnrollPerson persons : person) {

            list.add(persons);
        }

        return list;
    }

    public ObservableList<EnrollPerson> personAdvancedSearch(EnrollPerson person) {

        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        ObservableList<EnrollPerson> list = FXCollections.observableArrayList();
        String query = "";
        String mainquery = "SELECT D FROM EnrollPerson D WHERE 0 = 0 ";
        if (person != null) {
            if (isNullOrEmpty(person.getFirstNameEn())) {
                query = query + "AND D.firstNameEn ='" + person.getFirstNameEn() + "'";
            }
            if (isNullOrEmpty(person.getFatherName())) {
                query = query + "AND D.fatherName ='" + person.getFatherName() + "'";
            }
            if (isNullOrEmpty(person.getMotherName())) {
                query = query + "AND D.motherName ='" + person.getMotherName() + "'";
            }
            if (isNullOrEmpty(person.getSpouseName())) {
                query = query + "AND D.spouseName ='" + person.getSpouseName() + "'";
            }
//            if (isNullOrEmpty(person.getMobileNumber())) {
//                query = query + "AND D.mobileNumber ='" + person.getMobileNumber() + "'";
//            }
            if (isNullOrEmpty(person.getMobileNumber())) {
                query = query + "AND D.mobileNumber ='" + person.getMobileNumber() + "'";
            }
            if (isNullOrEmpty(person.getNationalId())) {
                query = query + "AND D.nationalId ='" + person.getNationalId() + "'";
            }
            if (person.getGender() != null) {
                query = query + "AND D.gender.id='" + person.getGender().getId() + "'";
            }
            if (person.getPermanentDivisionId()!= null) {
                query = query + "AND D.permanentDivisionId.id='" + person.getPermanentDivisionId().getId() + "'";
            }
            if (person.getPermanentDistrictId()!= null) {
                query = query + "AND D.permanentDistrictId.id='" + person.getPermanentDistrictId().getId() + "'";
            }
            if (person.getPermanentUpazilaId()!= null) {
                query = query + "AND D.permanentUpazilaId.id='" + person.getPermanentUpazilaId().getId() + "'";
            }
            if (person.getNationalityId() != null) {
                query = query + "AND D.nationalityId.id='" + person.getNationalityId().getId() + "'";
            }

            List<EnrollPerson> ePerson = em.createQuery(mainquery + query, EnrollPerson.class).getResultList();
            for (EnrollPerson persons : ePerson) {
                list.add(persons);
            }
        }

        return list;

    }

    public boolean addBiometric(EnrollPersonBiometric biometric) {
        boolean result = false;
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        //System.out.println("com.istl.dao.imp.ParsonDAOImp.addBiometric()" + biometric.getId());
        if (biometric != null) {
            try {
                em.getTransaction().begin();
                em.persist(biometric);
                em.getTransaction().commit();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public boolean personUpdate(EnrollPerson person) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        if (person != null) {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            em.clear();
            return true;
        }
        return false;
    }

    public boolean personUpdate(EnrollPerson person, EnrollPersonBiometric biometric) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        if (person != null) {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            em.clear();

            em.getTransaction().begin();
            em.merge(biometric);
            em.getTransaction().commit();
            em.clear();

            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(String str) {
        if (str != null & !str.isEmpty()) {
            return true;
        }
        return false;
    }

    public ObservableList<EnrollPerson> getPersonByStatus(Integer id) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        ObservableList<EnrollPerson> person = FXCollections.observableArrayList();

        List<EnrollPerson> ePerson = em.createQuery("SELECT D FROM EnrollPerson D WHERE D.status NOT LIKE " + "'%," + id + "%,' AND D.isCompleted = 1 ORDER BY D.id DESC").getResultList();

        for (EnrollPerson persons : ePerson) {
            person.add(persons);
            System.out.println("Name  : "+persons.getFirstNameEn()+"Status : "+persons.getStatus());
        }
        return person;
    }
    public EnrollPerson getPersonById(Integer id)
    {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        EnrollPerson p = new EnrollPerson();
         List<EnrollPerson> ePerson = em.createNamedQuery("EnrollPerson.findById").setParameter("id", id).getResultList();
        for (EnrollPerson enrollPerson : ePerson) {
            p = enrollPerson;
        }
        return p;
    }

    @Override
    public boolean nidMatching(String str,EnrollPerson person) {
         emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        EnrollPerson p = new EnrollPerson();
         List<EnrollPerson> ePerson = em.createNamedQuery("EnrollPerson.findByNationalId").setParameter("nationalId", str).getResultList();
        for (EnrollPerson enrollPerson : ePerson) {
            p = enrollPerson;
        }
        if (p.getId() == null) {
            return false;
        }
        else
        {
            if (person.getId() == p.getId()) {
                return false;
            }
            else
            {
                return true;
            }
        }
       
    }

    @Override
    public boolean mobileMatching(String str,EnrollPerson person) {
         emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        EnrollPerson p = new EnrollPerson();
         List<EnrollPerson> ePerson = em.createNamedQuery("EnrollPerson.findByMobileNumber").setParameter("mobileNumber", str).getResultList();
        for (EnrollPerson enrollPerson : ePerson) {
            p = enrollPerson;
        }
         if (p.getId() == null) {
            return false;
        }
        else
        {
            if (person.getId() == p.getId()) {
                return false;
            }
            else
            {
                return true;
            }
        }
    }

}
