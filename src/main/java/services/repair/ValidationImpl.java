/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services.repair;

import org.diachron.repair.master.Results;
import org.diachron.repair.master.Validation;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.Configuration;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author rousakis
 */
@Path("validation")
public class ValidationImpl {

    /** Creates a new instance of ValidationImpl */
    public ValidationImpl() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validationJSON(String inputMessage) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(inputMessage);
            if (jsonObject.size() != 3) {
                String message = "JSON input message should have exactly 3 arguments.";
                String json = "{ \"Success\" : false, "
                        + "\"Message\" : \"" + message + "\" }";
                return Response.status(400).entity(json).build();
            } else {
                String dataset = (String) jsonObject.get("Dataset");
                String constOntology = (String) jsonObject.get("Ontology_w_constraints");
                Boolean getInvalidities = (Boolean) jsonObject.get("GetInvalidities");
                if (dataset == null || constOntology == null || getInvalidities == null) {
                    throw new ParseException(-1);
                }
                ///
                Results result = null;
                JSONObject obj = new JSONObject();
//                    result = Validate.execute(properties, dataset, constOntology, true);
                String virtuoso = Configuration.PROPERTIES.getProperty("Repository_IP");
                String username = Configuration.PROPERTIES.getProperty("Repository_Username");
                String password = Configuration.PROPERTIES.getProperty("Repository_Password");
                String port = Configuration.PROPERTIES.getProperty("Repository_Port");
                Validation validation = new Validation();
                String[] arguments = {virtuoso, port, username, password, dataset, constOntology, Boolean.toString(true)};
                result = validation.run(arguments);
                obj.put("Success", true);
                obj.put("Result", result.getType());
                if (getInvalidities) {
                    obj.put("Invalidities", "[" + result.getStringBuilder() + "]");
                } else {
                    obj.put("Invalidities", "[" + new StringBuilder() + "]");
                }
                //String json = "{ \"Success\" : true, \"Result\" : " + result.getType() + ", \"Invalidities\" : [" + result.getStringBuilder() + "] }";
                return Response.status(200).entity(obj.toJSONString()).build();
            }
        } catch (ParseException ex) {
            String message = "JSON input message could not be parsed.";
            String json = "{ \"Success\" : false, "
                    + "\"Message\" : \"" + message + "\" }";
            return Response.status(400).entity(json).build();
        }
    }

    /**
     * Retrieves representation of an instance of test.ValidationImpl
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson() {
        String message = "OLA OK";
        String json = "{ \"Success\" : true, "
                + "\"Message\" : \"" + message + "\" }";
        return Response.status(200).entity(json).build();
    }
}
