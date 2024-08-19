    package com.example.claro_application.repositories;

    import com.example.claro_application.entities.Customer;
    import com.example.claro_application.entities.Plan;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.io.Serializable;
    import java.util.List;

    @Repository("customerRepository")
    public interface ICustomerRepository extends JpaRepository<Customer, Serializable> {
        public abstract Customer findById (int id);

        //Devuelve una lista de Customer con un determinado plan que llega por parámetro
        public abstract List<Customer> findByPlan (Plan plan);
    }
