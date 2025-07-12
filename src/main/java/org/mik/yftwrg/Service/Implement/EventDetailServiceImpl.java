package org.mik.yftwrg.Service.Implement;

import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Event;
import org.mik.yftwrg.Entity.EventDetail;
import org.mik.yftwrg.Exceptions.ResourceNotFoundException;
import org.mik.yftwrg.Repositories.EventDetailRepository;
import org.mik.yftwrg.Service.EventDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventDetailServiceImpl implements EventDetailService {
    private final EventDetailRepository eventDetailRepository;

    @Override
    public List<EventDetail> getAllEventDetails(){ return eventDetailRepository.findAll();}
    @Override
    public EventDetail getEventDetailById(Long id){
        return eventDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id" + id));
    }

    @Override
    public EventDetail saveEventDetail(EventDetail EV){
        return eventDetailRepository.save(EV);
    }

    @Override
    public EventDetail updateEventDetail(Long id, EventDetail eventDetail){
        EventDetail existingDetail = eventDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detail not found"));

        existingDetail.setDescription(eventDetail.getDescription());
        existingDetail.setLocationNote(eventDetail.getLocationNote());
//        existingDetail.setEvent(eventDetail.getEvent()); // if event can also be edited

        return eventDetailRepository.save(eventDetail);
    }
    @Override
    public void deleteEventDetail(Long id){
        eventDetailRepository.deleteById(id);
    }
}
