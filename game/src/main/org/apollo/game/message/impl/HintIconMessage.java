package org.apollo.game.message.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

import org.apollo.game.model.Position;
import org.apollo.net.message.Message;

/**
 * A {@link Message} that displays a hint icon over an Npc, tile, or player.
 *
 * @author Major
 */
public final class HintIconMessage extends Message {

	// TODO identify the other types.

	/**
	 * The type of a HintIcon.
	 */
	public enum Type {

		/**
		 * A HintIcon that hovers over an Npc.
		 */
		NPC(1),

		/**
		 * A HintIcon that hovers over a Player.
		 */
		PLAYER(10);

		/**
		 * The integer value of this type.
		 */
		private final int value;

		/**
		 * Creates the Type.
		 *
		 * @param value The value.
		 */
		private Type(int value) {
			this.value = value;
		}

		/**
		 * Gets the value of this type.
		 *
		 * @return The value.
		 */
		public int getValue() {
			return value;
		}

	}

	/**
	 * Creates a HintIconMessage for the Npc with the specified index.
	 *
	 * @param index The index of the Npc.
	 * @return The HintIconMessage.
	 */
	public static HintIconMessage forNpc(int index) {
		return new HintIconMessage(Type.NPC, OptionalInt.of(index), Optional.empty());
	}

	/**
	 * Creates a HintIconMessage for the Player with the specified index.
	 *
	 * @param index The index of the Player.
	 * @return The HintIconMessage.
	 */
	public static HintIconMessage forPlayer(int index) {
		return new HintIconMessage(Type.PLAYER, OptionalInt.of(index), Optional.empty());
	}

	/**
	 * Creates a HintIconMessage that removes the current Npc hint icon.
	 *
	 * @return The HintIconMessage.
	 */
	public static HintIconMessage resetNpc() {
		return forNpc(-1);
	}

	/**
	 * Creates a HintIconMessage that removes the current Player hint icon.
	 *
	 * @return The HintIconMessage.
	 */
	public static HintIconMessage resetPlayer() {
		return forPlayer(-1);
	}

	/**
	 * The index of the Mob, if applicable.
	 */
	private final OptionalInt index;

	/**
	 * The Position of the tile, if applicable.
	 */
	private final Optional<Position> position;

	/**
	 * The Type of entity this HintIconMessage is directed at.
	 */
	private final Type type;

	/**
	 * Creates the HintIconMessage.
	 *
	 * @param type The {@link Type} of this HintIconMessage.
	 * @param index The index of the Mob, if applicable.
	 * @param position The Position of the tile, if applicable.
	 */
	private HintIconMessage(Type type, OptionalInt index, Optional<Position> position) {
		this.type = type;
		this.index = index;
		this.position = position;
	}

	/**
	 * Gets the index of the entity, if applicable.
	 *
	 * @return The index.
	 * @throws NoSuchElementException If no index is available for this HintIcon.
	 */
	public int getIndex() {
		return index.getAsInt();
	}

	/**
	 * Gets the {@link Position} of the tile, if applicable.
	 *
	 * @return The Position.
	 * @throws NoSuchElementException If no Position is available for this HintIcon.
	 */
	public Position getPosition() {
		return position.get();
	}

	/**
	 * Gets the type this HintIconMessage is directed at.
	 *
	 * @return The type.
	 */
	public Type getType() {
		return type;
	}

}