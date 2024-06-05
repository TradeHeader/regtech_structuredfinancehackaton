package cdm.event.workflow;

import cdm.event.workflow.MessageInformation;
import cdm.event.workflow.MessageInformation.MessageInformationBuilder;
import cdm.event.workflow.MessageInformation.MessageInformationBuilderImpl;
import cdm.event.workflow.MessageInformation.MessageInformationImpl;
import cdm.event.workflow.meta.MessageInformationMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * This class corresponds to the components of the FpML MessageHeader.model.
 * @version ${project.version}
 */
@RosettaDataType(value="MessageInformation", builder=MessageInformation.MessageInformationBuilderImpl.class, version="${project.version}")
public interface MessageInformation extends RosettaModelObject {

	MessageInformationMeta metaData = new MessageInformationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A unique identifier assigned to the message.
	 */
	FieldWithMetaString getMessageId();
	/**
	 * The identifier for the originator of a message instance.
	 */
	FieldWithMetaString getSentBy();
	/**
	 * The identifier(s) for the recipient(s) of a message instance.
	 */
	List<? extends FieldWithMetaString> getSentTo();
	/**
	 * A unique identifier (within the specified coding scheme) giving the details of some party to whom a copy of this message will be sent for reference.
	 */
	List<? extends FieldWithMetaString> getCopyTo();

	/*********************** Build Methods  ***********************/
	MessageInformation build();
	
	MessageInformation.MessageInformationBuilder toBuilder();
	
	static MessageInformation.MessageInformationBuilder builder() {
		return new MessageInformation.MessageInformationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MessageInformation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MessageInformation> getType() {
		return MessageInformation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("messageId"), processor, FieldWithMetaString.class, getMessageId());
		processRosetta(path.newSubPath("sentBy"), processor, FieldWithMetaString.class, getSentBy());
		processRosetta(path.newSubPath("sentTo"), processor, FieldWithMetaString.class, getSentTo());
		processRosetta(path.newSubPath("copyTo"), processor, FieldWithMetaString.class, getCopyTo());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MessageInformationBuilder extends MessageInformation, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateMessageId();
		FieldWithMetaString.FieldWithMetaStringBuilder getMessageId();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSentBy();
		FieldWithMetaString.FieldWithMetaStringBuilder getSentBy();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSentTo(int _index);
		List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getSentTo();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCopyTo(int _index);
		List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getCopyTo();
		MessageInformation.MessageInformationBuilder setMessageId(FieldWithMetaString messageId0);
		MessageInformation.MessageInformationBuilder setMessageIdValue(String messageId1);
		MessageInformation.MessageInformationBuilder setSentBy(FieldWithMetaString sentBy0);
		MessageInformation.MessageInformationBuilder setSentByValue(String sentBy1);
		MessageInformation.MessageInformationBuilder addSentTo(FieldWithMetaString sentTo0);
		MessageInformation.MessageInformationBuilder addSentTo(FieldWithMetaString sentTo1, int _idx);
		MessageInformation.MessageInformationBuilder addSentToValue(String sentTo2);
		MessageInformation.MessageInformationBuilder addSentToValue(String sentTo3, int _idx);
		MessageInformation.MessageInformationBuilder addSentTo(List<? extends FieldWithMetaString> sentTo4);
		MessageInformation.MessageInformationBuilder setSentTo(List<? extends FieldWithMetaString> sentTo5);
		MessageInformation.MessageInformationBuilder addSentToValue(List<? extends String> sentTo6);
		MessageInformation.MessageInformationBuilder setSentToValue(List<? extends String> sentTo7);
		MessageInformation.MessageInformationBuilder addCopyTo(FieldWithMetaString copyTo0);
		MessageInformation.MessageInformationBuilder addCopyTo(FieldWithMetaString copyTo1, int _idx);
		MessageInformation.MessageInformationBuilder addCopyToValue(String copyTo2);
		MessageInformation.MessageInformationBuilder addCopyToValue(String copyTo3, int _idx);
		MessageInformation.MessageInformationBuilder addCopyTo(List<? extends FieldWithMetaString> copyTo4);
		MessageInformation.MessageInformationBuilder setCopyTo(List<? extends FieldWithMetaString> copyTo5);
		MessageInformation.MessageInformationBuilder addCopyToValue(List<? extends String> copyTo6);
		MessageInformation.MessageInformationBuilder setCopyToValue(List<? extends String> copyTo7);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("messageId"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getMessageId());
			processRosetta(path.newSubPath("sentBy"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getSentBy());
			processRosetta(path.newSubPath("sentTo"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getSentTo());
			processRosetta(path.newSubPath("copyTo"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCopyTo());
		}
		

		MessageInformation.MessageInformationBuilder prune();
	}

	/*********************** Immutable Implementation of MessageInformation  ***********************/
	class MessageInformationImpl implements MessageInformation {
		private final FieldWithMetaString messageId;
		private final FieldWithMetaString sentBy;
		private final List<? extends FieldWithMetaString> sentTo;
		private final List<? extends FieldWithMetaString> copyTo;
		
		protected MessageInformationImpl(MessageInformation.MessageInformationBuilder builder) {
			this.messageId = ofNullable(builder.getMessageId()).map(f->f.build()).orElse(null);
			this.sentBy = ofNullable(builder.getSentBy()).map(f->f.build()).orElse(null);
			this.sentTo = ofNullable(builder.getSentTo()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.copyTo = ofNullable(builder.getCopyTo()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("messageId")
		public FieldWithMetaString getMessageId() {
			return messageId;
		}
		
		@Override
		@RosettaAttribute("sentBy")
		public FieldWithMetaString getSentBy() {
			return sentBy;
		}
		
		@Override
		@RosettaAttribute("sentTo")
		public List<? extends FieldWithMetaString> getSentTo() {
			return sentTo;
		}
		
		@Override
		@RosettaAttribute("copyTo")
		public List<? extends FieldWithMetaString> getCopyTo() {
			return copyTo;
		}
		
		@Override
		public MessageInformation build() {
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder toBuilder() {
			MessageInformation.MessageInformationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MessageInformation.MessageInformationBuilder builder) {
			ofNullable(getMessageId()).ifPresent(builder::setMessageId);
			ofNullable(getSentBy()).ifPresent(builder::setSentBy);
			ofNullable(getSentTo()).ifPresent(builder::setSentTo);
			ofNullable(getCopyTo()).ifPresent(builder::setCopyTo);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MessageInformation _that = getType().cast(o);
		
			if (!Objects.equals(messageId, _that.getMessageId())) return false;
			if (!Objects.equals(sentBy, _that.getSentBy())) return false;
			if (!ListEquals.listEquals(sentTo, _that.getSentTo())) return false;
			if (!ListEquals.listEquals(copyTo, _that.getCopyTo())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (messageId != null ? messageId.hashCode() : 0);
			_result = 31 * _result + (sentBy != null ? sentBy.hashCode() : 0);
			_result = 31 * _result + (sentTo != null ? sentTo.hashCode() : 0);
			_result = 31 * _result + (copyTo != null ? copyTo.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MessageInformation {" +
				"messageId=" + this.messageId + ", " +
				"sentBy=" + this.sentBy + ", " +
				"sentTo=" + this.sentTo + ", " +
				"copyTo=" + this.copyTo +
			'}';
		}
	}

	/*********************** Builder Implementation of MessageInformation  ***********************/
	class MessageInformationBuilderImpl implements MessageInformation.MessageInformationBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder messageId;
		protected FieldWithMetaString.FieldWithMetaStringBuilder sentBy;
		protected List<FieldWithMetaString.FieldWithMetaStringBuilder> sentTo = new ArrayList<>();
		protected List<FieldWithMetaString.FieldWithMetaStringBuilder> copyTo = new ArrayList<>();
	
		public MessageInformationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("messageId")
		public FieldWithMetaString.FieldWithMetaStringBuilder getMessageId() {
			return messageId;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateMessageId() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (messageId!=null) {
				result = messageId;
			}
			else {
				result = messageId = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("sentBy")
		public FieldWithMetaString.FieldWithMetaStringBuilder getSentBy() {
			return sentBy;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSentBy() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (sentBy!=null) {
				result = sentBy;
			}
			else {
				result = sentBy = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("sentTo")
		public List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getSentTo() {
			return sentTo;
		}
		
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSentTo(int _index) {
		
			if (sentTo==null) {
				this.sentTo = new ArrayList<>();
			}
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			return getIndex(sentTo, _index, () -> {
						FieldWithMetaString.FieldWithMetaStringBuilder newSentTo = FieldWithMetaString.builder();
						return newSentTo;
					});
		}
		
		@Override
		@RosettaAttribute("copyTo")
		public List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getCopyTo() {
			return copyTo;
		}
		
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCopyTo(int _index) {
		
			if (copyTo==null) {
				this.copyTo = new ArrayList<>();
			}
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			return getIndex(copyTo, _index, () -> {
						FieldWithMetaString.FieldWithMetaStringBuilder newCopyTo = FieldWithMetaString.builder();
						return newCopyTo;
					});
		}
		
	
		@Override
		@RosettaAttribute("messageId")
		public MessageInformation.MessageInformationBuilder setMessageId(FieldWithMetaString messageId) {
			this.messageId = messageId==null?null:messageId.toBuilder();
			return this;
		}
		@Override
		public MessageInformation.MessageInformationBuilder setMessageIdValue(String messageId) {
			this.getOrCreateMessageId().setValue(messageId);
			return this;
		}
		@Override
		@RosettaAttribute("sentBy")
		public MessageInformation.MessageInformationBuilder setSentBy(FieldWithMetaString sentBy) {
			this.sentBy = sentBy==null?null:sentBy.toBuilder();
			return this;
		}
		@Override
		public MessageInformation.MessageInformationBuilder setSentByValue(String sentBy) {
			this.getOrCreateSentBy().setValue(sentBy);
			return this;
		}
		@Override
		public MessageInformation.MessageInformationBuilder addSentTo(FieldWithMetaString sentTo) {
			if (sentTo!=null) this.sentTo.add(sentTo.toBuilder());
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder addSentTo(FieldWithMetaString sentTo, int _idx) {
			getIndex(this.sentTo, _idx, () -> sentTo.toBuilder());
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder addSentToValue(String sentTo) {
			this.getOrCreateSentTo(-1).setValue(sentTo);
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder addSentToValue(String sentTo, int _idx) {
			this.getOrCreateSentTo(_idx).setValue(sentTo);
			return this;
		}
		@Override 
		public MessageInformation.MessageInformationBuilder addSentTo(List<? extends FieldWithMetaString> sentTos) {
			if (sentTos != null) {
				for (FieldWithMetaString toAdd : sentTos) {
					this.sentTo.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("sentTo")
		public MessageInformation.MessageInformationBuilder setSentTo(List<? extends FieldWithMetaString> sentTos) {
			if (sentTos == null)  {
				this.sentTo = new ArrayList<>();
			}
			else {
				this.sentTo = sentTos.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder addSentToValue(List<? extends String> sentTos) {
			if (sentTos != null) {
				for (String toAdd : sentTos) {
					this.addSentToValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder setSentToValue(List<? extends String> sentTos) {
			this.sentTo.clear();
			if (sentTos!=null) {
				sentTos.forEach(this::addSentToValue);
			}
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder addCopyTo(FieldWithMetaString copyTo) {
			if (copyTo!=null) this.copyTo.add(copyTo.toBuilder());
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder addCopyTo(FieldWithMetaString copyTo, int _idx) {
			getIndex(this.copyTo, _idx, () -> copyTo.toBuilder());
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder addCopyToValue(String copyTo) {
			this.getOrCreateCopyTo(-1).setValue(copyTo);
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder addCopyToValue(String copyTo, int _idx) {
			this.getOrCreateCopyTo(_idx).setValue(copyTo);
			return this;
		}
		@Override 
		public MessageInformation.MessageInformationBuilder addCopyTo(List<? extends FieldWithMetaString> copyTos) {
			if (copyTos != null) {
				for (FieldWithMetaString toAdd : copyTos) {
					this.copyTo.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("copyTo")
		public MessageInformation.MessageInformationBuilder setCopyTo(List<? extends FieldWithMetaString> copyTos) {
			if (copyTos == null)  {
				this.copyTo = new ArrayList<>();
			}
			else {
				this.copyTo = copyTos.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder addCopyToValue(List<? extends String> copyTos) {
			if (copyTos != null) {
				for (String toAdd : copyTos) {
					this.addCopyToValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder setCopyToValue(List<? extends String> copyTos) {
			this.copyTo.clear();
			if (copyTos!=null) {
				copyTos.forEach(this::addCopyToValue);
			}
			return this;
		}
		
		
		@Override
		public MessageInformation build() {
			return new MessageInformation.MessageInformationImpl(this);
		}
		
		@Override
		public MessageInformation.MessageInformationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MessageInformation.MessageInformationBuilder prune() {
			if (messageId!=null && !messageId.prune().hasData()) messageId = null;
			if (sentBy!=null && !sentBy.prune().hasData()) sentBy = null;
			sentTo = sentTo.stream().filter(b->b!=null).<FieldWithMetaString.FieldWithMetaStringBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			copyTo = copyTo.stream().filter(b->b!=null).<FieldWithMetaString.FieldWithMetaStringBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMessageId()!=null) return true;
			if (getSentBy()!=null) return true;
			if (getSentTo()!=null && !getSentTo().isEmpty()) return true;
			if (getCopyTo()!=null && !getCopyTo().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MessageInformation.MessageInformationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MessageInformation.MessageInformationBuilder o = (MessageInformation.MessageInformationBuilder) other;
			
			merger.mergeRosetta(getMessageId(), o.getMessageId(), this::setMessageId);
			merger.mergeRosetta(getSentBy(), o.getSentBy(), this::setSentBy);
			merger.mergeRosetta(getSentTo(), o.getSentTo(), this::getOrCreateSentTo);
			merger.mergeRosetta(getCopyTo(), o.getCopyTo(), this::getOrCreateCopyTo);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MessageInformation _that = getType().cast(o);
		
			if (!Objects.equals(messageId, _that.getMessageId())) return false;
			if (!Objects.equals(sentBy, _that.getSentBy())) return false;
			if (!ListEquals.listEquals(sentTo, _that.getSentTo())) return false;
			if (!ListEquals.listEquals(copyTo, _that.getCopyTo())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (messageId != null ? messageId.hashCode() : 0);
			_result = 31 * _result + (sentBy != null ? sentBy.hashCode() : 0);
			_result = 31 * _result + (sentTo != null ? sentTo.hashCode() : 0);
			_result = 31 * _result + (copyTo != null ? copyTo.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MessageInformationBuilder {" +
				"messageId=" + this.messageId + ", " +
				"sentBy=" + this.sentBy + ", " +
				"sentTo=" + this.sentTo + ", " +
				"copyTo=" + this.copyTo +
			'}';
		}
	}
}
