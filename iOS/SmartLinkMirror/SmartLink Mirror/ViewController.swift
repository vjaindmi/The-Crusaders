//
//  ViewController.swift
//  SmartLink Mirror
//
//  Created by dmi on 23/02/18.
//  Copyright © 2018 dmi. All rights reserved.
//

import UIKit

class ViewController: UIViewController, AdminPageTableViewCellDelegate {

  @IBOutlet weak var tableView: UITableView!
  var array: [Any] = []
  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view, typically from a nib.
    registerNib()
    array = ["clock", "compliments", "currentweather", "newsfeed"]
    tableView.tableFooterView = UIView()
    tableView.separatorStyle = .none
    tableView.isScrollEnabled = false
  }

  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
  }
  
  func registerNib() {
    tableView.register(UINib.init(nibName: "AdminPageTableViewCell",
                                  bundle: nil), forCellReuseIdentifier: "AdminPageTableViewCellID")
  }
  
  func toggleSwitchAction(_ sender: UISwitch) {
    if sender.isOn {
      self.requestToChangeControlStatus(tag: "\(sender.tag)", status: "SHOW")
    } else {
      self.requestToChangeControlStatus(tag: "\(sender.tag)", status: "HIDE")
    }
    

    
  }
    func requestToChangeControlStatus(tag:String,status:String){
        let module_name = "module_" + tag + "_\(self.array[Int(tag)!-1])"
        let requestURLString = Constant.baseURL + "?action=" + status + "&module=" + module_name
        
        var request = URLRequest(url: URL(string: requestURLString)!)
        request.httpMethod = "GET"
        let session = URLSession.shared
        
        session.dataTask(with: request) {data, response, err in
            print("Entered the completionHandler")
            }.resume()
    }

}

extension ViewController: UITableViewDataSource {
  func tableView(_ tableView: UITableView,
                 cellForRowAt indexPath: IndexPath) -> UITableViewCell {
    if let cell =  tableView.dequeueReusableCell(withIdentifier: "AdminPageTableViewCellID") as? AdminPageTableViewCell {
      cell.delegate = self
      if array.count > 0 {
        cell.layer.borderColor = UIColor.gray.cgColor
        cell.layer.borderWidth = 2.0
        cell.layer.cornerRadius = 5.0
        cell.label.text = array[indexPath.row] as? String
        cell.toggleSwitch.tag = indexPath.row+1
      }
      return cell
    }
    
    return  UITableViewCell()
  }
  
  func numberOfSections(in tableView: UITableView) -> Int {
    return 1
  }
  
  func tableView(_ tableView: UITableView,
                 numberOfRowsInSection section: Int) -> Int {
    return array.count
  }
}

extension ViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
      return 84
    }
}

