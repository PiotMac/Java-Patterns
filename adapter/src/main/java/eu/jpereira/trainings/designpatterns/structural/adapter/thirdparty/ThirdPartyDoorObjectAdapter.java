package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door {
    private final ThirdPartyDoor thirdPartyDoor = new ThirdPartyDoor();
    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            thirdPartyDoor.unlock(code);
        }
        catch (CannotUnlockDoorException e){
            throw new IncorrectDoorCodeException();
        }
        try {
            thirdPartyDoor.setState(ThirdPartyDoor.DoorState.OPEN);
        }
        catch (CannotChangeStateOfLockedDoor e) {
            throw new IncorrectDoorCodeException();
        }
    }

    @Override
    public void close() {
        try {
            thirdPartyDoor.setState(ThirdPartyDoor.DoorState.CLOSED);
        }
        catch (CannotChangeStateOfLockedDoor e) {

        }
    }

    @Override
    public boolean isOpen() {
        return thirdPartyDoor.getState() == ThirdPartyDoor.DoorState.OPEN;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        try {
            thirdPartyDoor.unlock(oldCode);
        }
        catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }
        if (newCode.equals(newCodeConfirmation))
        {
            try {
                thirdPartyDoor.setNewLockCode(newCode);
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
