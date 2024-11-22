package org.acme.infrastructure.persistence.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.acme.common.exceptions.ApiException;
import org.acme.common.exceptions.Errors;
import org.acme.infrastructure.persistence.entity.PaymentEntity;

import java.util.List;

@ApplicationScoped
public final class PaymentDao {

    @PersistenceContext
    EntityManager entityManager;


    /**
     * Adiciona um pagamento à base de dados.
     * Utiliza o método {@link EntityManager#merge(Object)} para persistir o
     * pagamento,
     * seja ele novo ou já existente, na base de dados.
     *
     * @param payment O objeto {@link PaymentEntity} que representa o pagamento a
     *                ser persistido.
     * @return O objeto {@link PaymentEntity} que foi persistido ou atualizado.
     * @throws ApiException Se ocorrer uma exceção de persistência durante o
     *                      processo de gravação na base de dados.
     */
    @Transactional
    public PaymentEntity create(final PaymentEntity payment) {
        try {
            entityManager.merge(payment);
        } catch (PersistenceException e) {
            throw new ApiException(Errors.PERSISTENCE_EXCEPTION.getMessage());
        }
        return payment;
    }

    /**
     * Recupera todos os pagamentos armazenados na base de dados.
     * Utiliza uma consulta JPQL para obter todos os registros da tabela de
     * pagamentos.
     *
     * @return Uma lista de objetos {@link PaymentEntity} que representam todos os
     *         pagamentos armazenados na base de dados.
     */
    public List<PaymentEntity> read() {
        return entityManager.createQuery("SELECT p FROM Payment p",
         PaymentEntity.class)
                .getResultList();
    }


    /**
     * Método para buscar Pagamentos por meio de seu Id.
     *
     * @param paymentId O ID do pagamento a ser encontrado.
     * @return O pagamento correspondente ao ID fornecido.
     * @throws ApiException Caso o pagamento não seja encontrado.
     */
    public PaymentEntity findById(final Long paymentId) {
        var payment = entityManager.find(PaymentEntity.class, paymentId);
        if (payment == null) {
            throw new ApiException(Errors.PAYMENT_NOT_FOUND.getExceptionWithArgs(paymentId));
        }
        return payment;
    }
}
