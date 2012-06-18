/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.upgrade.v5_0_0.UpgradeImageGallery;
import com.liferay.portal.upgrade.v5_0_0.UpgradeLayoutSet;
import com.liferay.portal.upgrade.v5_0_0.UpgradeSchema;
import com.liferay.portal.upgrade.v5_0_0.UpgradeSoftwareCatalog;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeProcess_5_0_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return ReleaseInfo.RELEASE_5_0_0_BUILD_NUMBER;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeSchema.class);
		upgrade(UpgradeImageGallery.class);
		upgrade(UpgradeLayoutSet.class);
		upgrade(UpgradeSoftwareCatalog.class);
	}

}