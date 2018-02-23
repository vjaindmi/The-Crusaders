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
    slider.value = 0
    slider.isContinuous = true
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
  @IBAction func sliderAction(_ sender: UISlider) {
    slider.value = sender.value
  }
  
  @IBAction func saveButtonAction(_ sender: Any) {
    
  }
}
