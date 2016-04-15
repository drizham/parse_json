import spray.json._
import DefaultJsonProtocol._

object parseJson {
	def main(args: Array[String]){

	// case classes for all the nested information in the 
	case class Ids(Ids: Seq[String])
	case class Id(Id: String)
	case class Name(Name: String)
	case class OnHand(OnHand: Int)
	case class LoyaltyId(LoyaltyId: Int)

	case class ProductPrice(RetailPrice: Double,
	                        RetailPriceSymbol: String,
	                        PromotionPrice: Double,
	                        PromotionPriceSymbol: String)

	case class Product(Name: String,
	                   ProductCategory: String,
	                   InventoryTrackingNumberType: String,
	                   InventoryTrackingNumber: String,
	                   ProductPrice: ProductPrice)

	case class Data(Content: Ids,
	                SfuRegistries: Ids,
	                AgeRange: Name,
	                Company: Id,
	                SensorType: Name,
	                StockLevel: OnHand,
	                Region: Name,
	                UnitType: Name,
	                Emotion: Name,
	                Shelves: Ids,
	                Customer: LoyaltyId,
	                DeviceRegistries: Seq[Map[String, String]],
	                ConnectorType: Name,
	                CompanyType: Name,
	                State: Name,
	                Gender: Name,
	                SensorRegistry: Name,
	                Race: Name,
	                Store: Id,
	                Products: Seq[Product])

	case class Element(EventId: String,
	                 Timestamp: String,
	                 StartTime: String,
	                 EndTime: String,
	                 ActiveStates: String,
	                 Created: String,
	                 Modified: String,
	                 Data: Data)

 // This is the code that is blowing up
 case class RootCollection(items: Array[Element]) extends IndexedSeq[Element]{
    def apply(index: Int) = items(index)
    def length = items.length
}

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val nameFormat = jsonFormat1(Name)
  implicit val productPriceFormat = jsonFormat4(ProductPrice)
  implicit val productFormat = jsonFormat5(Product)
  implicit val loyaltyIdFormat = jsonFormat1(LoyaltyId)
  implicit val onHandFormat = jsonFormat1(OnHand)
  implicit val idFormat = jsonFormat1(Id)
  implicit val idsFormat = jsonFormat1(Ids)
  implicit val dateFormat = jsonFormat20(Data)
  implicit val ElementFormat = jsonFormat8(Element)  
  implicit object RootCollectionFormat extends RootJsonFormat[RootCollection] {
    def read(value: JsValue) = RootCollection(value.convertTo[Array[Element]])
    def write(f: RootCollection) = JsArray(f.toJson)
  }
 }
 
 import MyJsonProtocol._

		println("Running Parse JSON")
		val input = scala.io.Source.fromFile("sample2.json")("UTF-8").mkString.parseJson
		//println("JSON string read:")
		//println(input)

		val jsonCollection = input.convertTo[RootCollection]

		// print some items
		jsonCollection.map(y => y.Data.Products.map(x => println(x)))
		println(jsonCollection.length)

	}
}