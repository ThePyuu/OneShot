package de.pyuu.oneshot.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.pyuu.oneshot.Main;

public class SQLStats {

	public static boolean playerExists(String uuid) {
		boolean has = false;
		try {
			ResultSet rs = Main.mysql
					.query("SELECT * FROM `Stats` WHERE `UUID` = '" + uuid
							+ "'");
			if (rs.next()) {
				has = true;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return has;
	}

	public static void createPlayer(String uuid) {

		if (!playerExists(uuid)) {
			Main.mysql
					.update("INSERT INTO Stats(UUID, KILLS, DEATHS) VALUES ('"
							+ uuid + "', '0', '0');");
		}

	}

	public static Integer getKills(String uuid) {
		Integer i = 0;

		if (playerExists(uuid)) {

			try {
				ResultSet rs = Main.mysql
						.query("SELECT * FROM Stats WHERE UUID = '" + uuid
								+ "'");
				if ((!rs.next())
						|| (Integer.valueOf(rs.getInt("KILLS")) == null));

				i = rs.getInt("KILLS");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			createPlayer(uuid);
			getKills(uuid);
		}

		return i;
	}

	public static Integer getDeaths(String uuid) {
		Integer i = 0;

		if (playerExists(uuid)) {

			try {
				ResultSet rs = Main.mysql
						.query("SELECT * FROM Stats WHERE UUID = '" + uuid
								+ "'");
				if ((!rs.next())
						|| (Integer.valueOf(rs.getInt("DEATHS")) == null))
					;

				i = rs.getInt("DEATHS");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			createPlayer(uuid);
			getDeaths(uuid);
		}

		return i;
	}

	public static void setKills(String uuid, Integer kills) {

		if (playerExists(uuid)) {
			Main.mysql.update("UPDATE Stats SET KILLS= '" + kills
					+ "' WHERE UUID= '" + uuid + "';");
		} else {
			createPlayer(uuid);
			setKills(uuid, kills);
		}
	}

	public static void setDeaths(String uuid, Integer deaths) {

		if (playerExists(uuid)) {
			Main.mysql.update("UPDATE Stats SET DEATHS= '" + deaths
					+ "' WHERE UUID= '" + uuid + "';");
		} else {
			createPlayer(uuid);
			setDeaths(uuid, deaths);
		}
	}

	public static void addKills(String uuid, Integer kills) {
		if (playerExists(uuid)) {
			setKills(
					uuid,
					Integer.valueOf(getKills(uuid).intValue()
							+ kills.intValue()));
		} else {
			createPlayer(uuid);
			addKills(uuid, kills);
		}
	}

	public static void addDeaths(String uuid, Integer deaths) {
		if (playerExists(uuid)) {
			setDeaths(
					uuid,
					Integer.valueOf(getDeaths(uuid).intValue()
							+ deaths.intValue()));
		} else {
			createPlayer(uuid);
			addDeaths(uuid, deaths);
		}
	}

	// =============================REMOVE !!!!!!!!!!!!!!!!!!!!!!!!

	public static void removeKills(String uuid, Integer kills) {
		if (playerExists(uuid)) {
			setKills(
					uuid,
					Integer.valueOf(getKills(uuid).intValue()
							- kills.intValue()));
		} else {
			createPlayer(uuid);
			removeKills(uuid, kills);
		}
	}

	public static void removeDeaths(String uuid, Integer deaths) {
		if (playerExists(uuid)) {
			setDeaths(
					uuid,
					Integer.valueOf(getDeaths(uuid).intValue()
							- deaths.intValue()));
		} else {
			createPlayer(uuid);
			removeDeaths(uuid, deaths);
		}
	}

}
