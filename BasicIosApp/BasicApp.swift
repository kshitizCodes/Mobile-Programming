import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var myTxt1: UITextField!
    @IBOutlet weak var myTxt2: UITextField!
    @IBOutlet weak var myLbl1: UILabel!
    
    @IBAction func btnClick(_ sender: Any) {
        if let text1 = myTxt1.text, let text2 = myTxt2.text, let number1 = Int(text1), let number2 = Int(text2) {
            let sum = number1 + number2
            myLbl1.text = String(sum)
            print("Sum is \(sum)")
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("loaded")
    }
}
