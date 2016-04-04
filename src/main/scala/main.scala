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

	case class claX2(EventId: String,
	                 Timestamp: String,
	                 StartTime: String,
	                 EndTime: String,
	                 ActiveStates: String,
	                 Created: String,
	                 Modified: String,
	                 Data: Data)

	 object MyJsonProtocol2 extends DefaultJsonProtocol {
		 implicit val nameFormat = jsonFormat1(Name)
		 implicit val productPriceFormat = jsonFormat4(ProductPrice)
		 implicit val productFormat = jsonFormat5(Product)
		 implicit val loyaltyIdFormat = jsonFormat1(LoyaltyId)
		 implicit val onHandFormat = jsonFormat1(OnHand)
		 implicit val idFormat = jsonFormat1(Id)
		 implicit val idsFormat = jsonFormat1(Ids)
		 implicit val dateFormat = jsonFormat20(Data)
		 implicit val claXFormat2 = jsonFormat8(claX2)
 		}

 	import MyJsonProtocol2._


		println("Running Parse JSON")
		val input = scala.io.Source.fromFile("sample1.json")("UTF-8").mkString.parseJson
		println("JSON string read:")
		//println(input.toString)

		val cx0 = input.convertTo[claX2]
		println(cx0.toString)

		// print some items
		cx0.Data.Products.map(x => println(x))


	}
}