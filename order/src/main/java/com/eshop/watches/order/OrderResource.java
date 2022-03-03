package com.eshop.watches.order;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.eshop.watches.order.utils.ShippingState;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/orders")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class OrderResource {

  @GET
  @Operation(summary = "Returns all the orders")
  @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = WatchOrder.class, type = SchemaType.ARRAY)))
  @APIResponse(responseCode = "204", description = "No Orders")
  @Counted(name = "countGetOrders")
  @Timed(name = "timeGetOrders", unit = MetricUnits.MILLISECONDS)
  public Response getOrders() {

    List<WatchOrder> orders = WatchOrder.listAll();
    if (orders.isEmpty()) {
      return Response.noContent().build();
    }
    return Response.ok(orders).build();
  }

  @POST
  @Operation(summary = "Create Order")
  @APIResponse(responseCode = "201")
  @Transactional
  public Response createOrder(@Valid WatchOrder order) {
    order.setShippingCode(null);
    order.setShippingStatus(ShippingState.Pending.getStateText());
    order.persist();
    return Response.created(URI.create("/orders/" + order.getId())).build();
  }

  @PUT
  @Path("/next/{id}")
  @Operation(summary = "Move order to next state")
  @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = WatchOrder.class, type = SchemaType.OBJECT)))
  @APIResponse(responseCode = "404", description = "Greeting not Found")
  @Transactional
  public Response updateOrderState(@PathParam("id") Long id) {
    WatchOrder existingOrder = WatchOrder.findById(id);
    if (existingOrder == null) {
      return Response.status(Status.NOT_FOUND.getStatusCode(), "Order not Found").build();
    }
    ShippingState currentState = ShippingState.getShippingState(existingOrder.getShippingStatus());
    if (ShippingState.Pending == currentState) {
      existingOrder.setShippingCode(this.newId());
    }
    existingOrder.setShippingStatus(currentState.nextState().getStateText());
    existingOrder.persist();
    return Response.ok(existingOrder).build();
  }

  private String newId() {
    // chose a Character random from this String
    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvxyz";

    // create StringBuffer size of AlphaNumericString
    StringBuilder sb = new StringBuilder(10);

    for (int i = 0; i < 10; i++) {

      // generate a random number between
      // 0 to AlphaNumericString variable length
      int index = (int) (AlphaNumericString.length()
          * Math.random());

      // add Character one by one in end of sb
      sb.append(AlphaNumericString
          .charAt(index));
    }

    return sb.toString();

  }

}
