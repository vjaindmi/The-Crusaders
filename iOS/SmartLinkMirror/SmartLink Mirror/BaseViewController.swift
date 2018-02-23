//
//  BaseViewController.swift
//  SmartLink Mirror
//
//  Created by dmi on 23/02/18.
//  Copyright © 2018 dmi. All rights reserved.
//

import UIKit

class BaseViewController: UIViewController {

  @IBOutlet weak var tableView: UITableView!
  var array: [Any] = []

  override func viewDidLoad() {
        super.viewDidLoad()
    array = ["SHUTDOWN", "REBOOT", "RESTART", "MONITOR ON", "MONITOR OFF", "REFRESH", "BRIGHTNESS", "HIDE/SHOW MODULE"]
    tableView.tableFooterView = UIView()
    tableView.separatorStyle = .none
        // Do any additional setup after loading the view.
    registerNib()
    self.navigationController?.navigationBar.tintColor = .white
    self.navigationController?.navigationBar.barStyle = .blackTranslucent
    self.navigationController?.navigationBar.barTintColor = .black
    UINavigationBar.appearance().titleTextAttributes = [NSAttributedStringKey.foregroundColor : UIColor.white]
    self.title = "SmartLink Mirror".uppercased()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
  
  func registerNib() {
    tableView.register(UITableViewCell.self, forCellReuseIdentifier: "CellID")
  }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
extension BaseViewController: UITableViewDataSource {
  func tableView(_ tableView: UITableView,
                 cellForRowAt indexPath: IndexPath) -> UITableViewCell {
    var cell = tableView.dequeueReusableCell(withIdentifier: "Cell")
    
    cell = UITableViewCell(style: .default, reuseIdentifier: "Cell")
    if array.count > 0 {
      cell?.layer.borderColor = UIColor.gray.cgColor
      cell?.layer.borderWidth = 2.0
      cell?.layer.cornerRadius = 5.0
      cell?.textLabel?.text = array[indexPath.row] as? String
      cell?.textLabel?.textColor = .white
        cell?.accessoryType = (indexPath.row>5) ? .disclosureIndicator : .none
    }
    cell?.backgroundColor = .black
    cell?.selectionStyle = .none
    return cell!
  }
  
  func numberOfSections(in tableView: UITableView) -> Int {
    return 1
  }
  
  func tableView(_ tableView: UITableView,
                 numberOfRowsInSection section: Int) -> Int {
    return array.count
  }
    func requestToChangeConfigureAction(status:String){
        let requestURLString = Constant.baseURL + "?action=" + status
        
        var request = URLRequest(url: URL(string: requestURLString)!)
        request.httpMethod = "GET"
        let session = URLSession.shared
        
        session.dataTask(with: request) {data, response, err in
            print("Entered the completionHandler")
            }.resume()
    }
}

extension BaseViewController: UITableViewDelegate {
  func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
    return 50
  }
  func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
    let storyboard = UIStoryboard.init(name: "Main", bundle: nil)
    
    switch indexPath.row {
    case 0:
        self.requestToChangeConfigureAction(status: "SHUTDOWN")
      break
    case 1:
        self.requestToChangeConfigureAction(status: "REBOOT")
      break
    case 2:
        self.requestToChangeConfigureAction(status: "RESTART")
      break
    case 3:
        self.requestToChangeConfigureAction(status: "MONITORON")
      break
    case 4:
        self.requestToChangeConfigureAction(status: "MONITOROFF")
      break
    case 5:
        self.requestToChangeConfigureAction(status: "REFRESH")
      break
    case 6:
      let viewcontroller = storyboard.instantiateViewController(withIdentifier: "BrigtnessControlView")
      self.navigationController?.pushViewController(viewcontroller, animated: true)
      break
    case 7:
      let viewcontroller = storyboard.instantiateViewController(withIdentifier: "HideShowTableView")
      self.navigationController?.pushViewController(viewcontroller, animated: true)
      break
    default:
      break
    }
  }
}
