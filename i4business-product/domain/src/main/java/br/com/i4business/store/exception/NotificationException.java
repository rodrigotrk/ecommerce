package br.com.i4business.store.exception;

import br.com.i4business.store.validation.handler.Notification;

public class NotificationException extends DomainException{
    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}
