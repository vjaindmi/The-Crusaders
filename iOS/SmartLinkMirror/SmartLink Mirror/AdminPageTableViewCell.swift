//
//  AdminPageTableViewCell.swift
//  SmartLink Mirror
//
//  Created by dmi on 23/02/18.
//  Copyright © 2018 dmi. All rights reserved.
//

import UIKit
protocol AdminPageTableViewCellDelegate {
  func toggleSwitchAction(_ sender: UISwitch)
}

class AdminPageTableViewCell: UITableViewCell {

  @IBOutlet weak var toggleSwitch: UISwitch!
  @IBOutlet weak var label: UILabel!
  var delegate: AdminPageTableViewCellDelegate?
  
  override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

  override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
  
  @IBAction func toggleAction(_ sender: UISwitch) {
    self.delegate?.toggleSwitchAction(sender)
  }
}
