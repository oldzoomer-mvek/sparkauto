package ru.oldzoomer.view.util;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DialogUtil {
    public static <T> void openAddDialog(Dialog dialog, FormLayout formLayout, BeanValidationBinder<T> binder,
                                         Button saveButton, Button cancelButton, T item) {
        setupDialog(dialog, formLayout, saveButton, cancelButton);
        binder.setBean(item);
        dialog.open();
    }

    public static <T> void openEditDialog(Dialog dialog, FormLayout formLayout, BeanValidationBinder<T> binder,
                                          Button saveButton, Button cancelButton, T item) {
        setupDialog(dialog, formLayout, saveButton, cancelButton);
        binder.setBean(item);
        dialog.open();
    }

    private static void setupDialog(Dialog dialog, FormLayout formLayout, Button saveButton, Button cancelButton) {
        formLayout.setAutoResponsive(true);

        dialog.add(formLayout);
        dialog.getFooter().add(new HorizontalLayout(saveButton, cancelButton));
        dialog.setCloseOnOutsideClick(false);
        dialog.setCloseOnEsc(true);
    }
}