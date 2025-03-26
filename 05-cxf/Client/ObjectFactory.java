
package com.example.restaurant;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.restaurant package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetMenu_QNAME = new QName("http://restaurant.example.com/", "getMenu");
    private final static QName _GetMenuResponse_QNAME = new QName("http://restaurant.example.com/", "getMenuResponse");
    private final static QName _PlaceOrder_QNAME = new QName("http://restaurant.example.com/", "placeOrder");
    private final static QName _PlaceOrderResponse_QNAME = new QName("http://restaurant.example.com/", "placeOrderResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.restaurant
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMenu }
     * 
     */
    public GetMenu createGetMenu() {
        return new GetMenu();
    }

    /**
     * Create an instance of {@link GetMenuResponse }
     * 
     */
    public GetMenuResponse createGetMenuResponse() {
        return new GetMenuResponse();
    }

    /**
     * Create an instance of {@link PlaceOrder }
     * 
     */
    public PlaceOrder createPlaceOrder() {
        return new PlaceOrder();
    }

    /**
     * Create an instance of {@link PlaceOrderResponse }
     * 
     */
    public PlaceOrderResponse createPlaceOrderResponse() {
        return new PlaceOrderResponse();
    }

    /**
     * Create an instance of {@link Dish }
     * 
     */
    public Dish createDish() {
        return new Dish();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMenu }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetMenu }{@code >}
     */
    @XmlElementDecl(namespace = "http://restaurant.example.com/", name = "getMenu")
    public JAXBElement<GetMenu> createGetMenu(GetMenu value) {
        return new JAXBElement<GetMenu>(_GetMenu_QNAME, GetMenu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMenuResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetMenuResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://restaurant.example.com/", name = "getMenuResponse")
    public JAXBElement<GetMenuResponse> createGetMenuResponse(GetMenuResponse value) {
        return new JAXBElement<GetMenuResponse>(_GetMenuResponse_QNAME, GetMenuResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlaceOrder }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PlaceOrder }{@code >}
     */
    @XmlElementDecl(namespace = "http://restaurant.example.com/", name = "placeOrder")
    public JAXBElement<PlaceOrder> createPlaceOrder(PlaceOrder value) {
        return new JAXBElement<PlaceOrder>(_PlaceOrder_QNAME, PlaceOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlaceOrderResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PlaceOrderResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://restaurant.example.com/", name = "placeOrderResponse")
    public JAXBElement<PlaceOrderResponse> createPlaceOrderResponse(PlaceOrderResponse value) {
        return new JAXBElement<PlaceOrderResponse>(_PlaceOrderResponse_QNAME, PlaceOrderResponse.class, null, value);
    }

}
