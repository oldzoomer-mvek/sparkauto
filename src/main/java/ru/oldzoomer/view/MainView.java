package ru.oldzoomer.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.RolesAllowed;

@Route("")
@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
public class MainView extends AppLayout {

    public MainView() {
        // Header
        H1 title = new H1("Спаркавто CRM");
        addToNavbar(title);

        // Drawer with navigation links
        RouterLink clientsLink = new RouterLink("Клиенты", ClientView.class);
        RouterLink ordersLink = new RouterLink("Заказы", OrderView.class);
        RouterLink worksLink = new RouterLink("Рaботы", WorkView.class);
        VerticalLayout drawerContent = new VerticalLayout(clientsLink, ordersLink, worksLink);
        addToDrawer(drawerContent);
    }
}

