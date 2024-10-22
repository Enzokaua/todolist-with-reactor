package com.develop.webflux.repository;

import com.develop.webflux.config.ConnectionFactory;
import com.develop.webflux.context.FluxRequest;
import com.develop.webflux.context.FluxResponse;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FluxRepository {

    private Connection connection;

    public FluxRepository() {
        this.connection = ConnectionFactory.getConnection();
    }
    public final String INSERT_CARDS = " INSERT INTO CARDS (CREATOR_CARD, MESSAGE_CARD, PRIORITY_CARD) VALUES (?, ?, ?)";
    public final String SEARCH_CARD = " SELECT * FROM CARDS WHERE CARD_ID = ?";
    public final String SEARCH_CARDS = " SELECT * FROM CARDS";
    public final String UPDATE_CARDS = " UPDATE CARDS SET CREATOR_CARD = ?, MESSAGE_CARD = ?, PRIORITY_CARD = ? WHERE CARD_ID = ?";
    public final String DELETE_CARD = " DELETE FROM CARDS WHERE CARD_ID = ?";

    public boolean droppingCard(String cardId) {
        try (PreparedStatement pst = connection.prepareStatement(DELETE_CARD)) {
            pst.setString(1, cardId);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertCard(FluxRequest request) {
        try (PreparedStatement pst = connection.prepareStatement(INSERT_CARDS)) {
            pst.setString(1, request.getCreatorCard());
            pst.setString(2, request.getMessageCard());
            pst.setString(3, request.getPriorityCard());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCard(FluxRequest request, String cardId) {
        try (PreparedStatement pst = connection.prepareStatement(UPDATE_CARDS)) {
            pst.setString(1, request.getCreatorCard());
            pst.setString(2, request.getMessageCard());
            pst.setString(3, request.getPriorityCard());
            pst.setString(4, cardId);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public FluxResponse searchCard(String cardId) {
        try (PreparedStatement pst = connection.prepareStatement(SEARCH_CARD)) {
            pst.setString(1, cardId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return FluxResponse.builder()
                        .cardId(rs.getString("CARD_ID"))
                        .build();
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FluxResponse> searchCards() {
        List<FluxResponse> cards = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SEARCH_CARDS);
            while (rs.next()) {
                FluxResponse card = FluxResponse.builder()
                        .cardId(rs.getString("CARD_ID"))
                        .build();
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }
}
