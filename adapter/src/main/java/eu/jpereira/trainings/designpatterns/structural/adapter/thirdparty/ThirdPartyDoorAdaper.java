package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdaper extends ThirdPartyDoor implements Door {
    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            super.unlock(code);
        }
        catch (CannotUnlockDoorException e){
            throw new IncorrectDoorCodeException();
        }
        try {
            super.setState(DoorState.OPEN);
        }
        catch (CannotChangeStateOfLockedDoor e) {
            throw new IncorrectDoorCodeException();
        }
    }

    @Override
    public void close() {
        try {
            super.setState(DoorState.CLOSED);
        }
        catch (CannotChangeStateOfLockedDoor e) {

        }
    }

    @Override
    public boolean isOpen() {
        return super.getState() == DoorState.OPEN;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        try {
            super.unlock(oldCode);
        }
        catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }
        if (newCode.equals(newCodeConfirmation))
        {
            try {
                super.setNewLockCode(newCode);
            }
            catch (CannotChangeCodeForUnlockedDoor e) {

            }
        }
        else {
            throw new CodeMismatchException();
        }
    }

    @Override
    public boolean testCode(String code) {
        try {
            open(code);
        }
        catch (IncorrectDoorCodeException e) {

        }
        return isOpen();
    }
}
