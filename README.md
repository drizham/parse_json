Working project of trying to parse a complex JSON structure into Scala case class

I have been able to parse one instance of the event as denoted by "EventId".
But do not know how to parse it if there were several events denoted by different "EventId" s.
I dont have a proper sample of what the JSON would look like if there were several events.

- sample1.json sample json file with only one element
- sample2.json made up sample json file with more than one element (array)

Getting error from this part of the code:
 import DefaultJsonProtocol._
 import spray.json.JsValue
 
 object MyJsonProtocol3 extends DefaultJsonProtocol {
 implicit val nameFormat = jsonFormat1(Name)
 implicit val productPriceFormat = jsonFormat4(ProductPrice)
 implicit val productFormat = jsonFormat5(Product)
 implicit val loyaltyIdFormat = jsonFormat1(LoyaltyId)
 implicit val onHandFormat = jsonFormat1(OnHand)
 implicit val idFormat = jsonFormat1(Id)
 implicit val idsFormat = jsonFormat1(Ids)
 implicit val dateFormat = jsonFormat20(Data)
 implicit val claXFormat2 = jsonFormat8(claX2)
 implicit object claX2Collection extends RootJsonFormat[claX2Collection] {
    def read(value: JsValue) = claX2Collection(value.convertTo[Array[claX2]])
    def write(f: claX2Collection) = ??? // got to fix this
  }
 
 }
 import MyJsonProtocol3._

 Error:
 <console>:111: error: $iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.$iwC.MyJsonProtocol3.claX2Collection.type does not take parameters
           def read(value: JsValue) = claX2Collection(value.convertTo[Array[claX2]])
                                                     ^
                                                     