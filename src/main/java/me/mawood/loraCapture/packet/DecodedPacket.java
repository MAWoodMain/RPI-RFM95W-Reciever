package me.mawood.loraCapture.packet;

import me.mawood.loraCapture.packet.block.InvalidBlockException;
import me.mawood.loraCapture.packet.segment.InvalidSegmentException;
import me.mawood.loraCapture.packet.segment.Segment;
import me.mawood.loraCapture.packet.segment.segments.BatterySegment;
import me.mawood.loraCapture.packet.segment.segments.UptimeSegment;
import me.mawood.loraCapture.persistence.Persistable;
import me.mawood.loraCapture.persistence.loRaPacket.LoRaPacket;
import me.mawood.loraCapture.persistence.loRaPacket.RxInfo;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "decoded_packet")
public class DecodedPacket implements Persistable
{
    int packetId;
    private LoRaPacket origin;
    private Set<Segment> segments;

    public DecodedPacket()
    {

    }

    public DecodedPacket(LoRaPacket loraPacket) throws InvalidSegmentException, PacketException, InvalidBlockException
    {
        this.origin = loraPacket;
        PacketStreamReader psr = new PacketStreamReader(Base64.getDecoder().decode(loraPacket.getData().getBytes()));
        this.segments = new HashSet<>();
        this.segments.addAll(Arrays.asList(psr.getSegments()));
        this.segments.forEach(s -> s.setPacket(this));
    }

    @Id
    @Column(name = "packetId")
    @GeneratedValue
    public int getPacketId()
    {
        return packetId;
    }

    public void setPacketId(int packetId)
    {
        this.packetId = packetId;
    }

    @ManyToOne
    @JoinColumn(name="origin", nullable=false)
    public LoRaPacket getOrigin()
    {
        return origin;
    }

    public void setOrigin(LoRaPacket origin)
    {
        this.origin = origin;
    }


    @OneToMany(mappedBy="packet", cascade = CascadeType.ALL)
    public Set<Segment> getSegments()
    {
        return segments;
    }

    public void setSegments(Set<Segment> rxInfos)
    {
        this.segments = segments;
    }

    @Override
    public void prepare()
    {

    }

    @Override
    public String toString()
    {
        return "DecodedPacket{" +
                "packetId=" + packetId +
                ", origin=" + origin +
                ", segments=" + segments +
                '}';
    }
}
