package ssvv.project.validation;

import ssvv.project.domain.Nota;

public class NotaValidator implements Validator<Nota> {
    public void validate(Nota nota) throws ValidationException {
        if (nota.getID().getObject1() == null || nota.getID().equals("")) {
            throw new ValidationException("ID Student invalid! \n");
        }
        if (nota.getID().getObject2() == null || nota.getID().equals("")) {
            throw new ValidationException("ID Tema invalid! \n");
        }
        if (nota.getNota() < 0 || nota.getNota() > 10) {
            throw new ValidationException("Nota invalida! \n"+nota.getNota());
        }
        if (nota.getSaptamanaPredare() < 0) {
            throw new ValidationException("Saptamana de predare invalida! \n");
        }
    }
}
