//
//  BrigtnessControlViewController.swift
//  SmartLink Mirror
//
//  Created by dmi on 23/02/18.
//  Copyright © 2018 dmi. All rights reserved.
//

import UIKit

class BrigtnessControlViewController: UIViewController {

  @IBOutlet weak var slider: UISlider!
  
  override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    configureDefaultSlider()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
  
    func configureDefaultSlider() {
    slider.minimumValue = 0
    slider.maximumValue = 200
    slider.value = 100
    slider.isContinuous = true
    }
  @IBAction func sliderAction(_ sender: UISlider) {
    slider.value = sender.value
  }
  
  @IBAction func saveButtonAction(_ sender: Any) {
      self.requestToChangeConfigureAction(status: "BRIGHTNESS")
  }
    func requestToChangeConfigureAction(status:String){
        let requestURLString = Constant.baseURL + "?action=" + status + "&value=" + "\(Int(self.slider.value))"
        
        var request = URLRequest(url: URL(string: requestURLString)!)
        request.httpMethod = "GET"
        let session = URLSession.shared
        
        session.dataTask(with: request) {data, response, err in
            print("Entered the completionHandler")

            }.resume()
    }
}
